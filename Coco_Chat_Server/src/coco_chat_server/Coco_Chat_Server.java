package coco_chat_server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import db_conection_package.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import returned_models.*;

public class Coco_Chat_Server {

    public static void main(String[] args){
        try{
            ServerSocket serverSocket = new ServerSocket(1234);

            Map<String, Integer> intentosInicioSesion = new HashMap<>();
            
            while(true)
            {
               System.out.println("Esperando...");
               Socket clientSocket = serverSocket.accept();
               System.out.println("Conexion recibida");
               
               Thread clienteThread = new Thread(() -> 
               {
                   try{
                       System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());
                       
                       InputStream inputStream = clientSocket.getInputStream();
                       DataInputStream mensaje = new DataInputStream(inputStream);
                       String clienteConectado =  clientSocket.getInetAddress().getHostAddress();
                       if(!(intentosInicioSesion.containsKey(clienteConectado)))
                       {
                            intentosInicioSesion.put(clienteConectado, 0);
                       }
                       if(mensaje.equals("Todas las forms cerradas"))
                       {
                       }
                       DataInputStream salidaFuncion = new DataInputStream(clientSocket.getInputStream());
                       System.out.println("Salida Funcion: " + salidaFuncion);

                       String funcion = salidaFuncion.readUTF();
                       System.out.println("Funcion: " + funcion);

                       if(funcion.equals("register"))
                        {
                             ObjectInputStream infoReceived = new ObjectInputStream(clientSocket.getInputStream());
                             try {
                               Object objectReceived = infoReceived.readObject();
                               Usuario registerDetails  = (Usuario)objectReceived;
                               System.out.println(registerDetails.nombre);
                               UsuarioDAO usuarioDAO = new UsuarioDAO();
                               usuarioDAO.RegistrarUsuario(registerDetails);
                             } catch (ClassNotFoundException ex) {
                                 Logger.getLogger(Coco_Chat_Server.class.getName()).log(Level.SEVERE, null, ex);
                             }
                        }
                        if(funcion.equals("login"))
                        {
                             ObjectInputStream infoReceived = new ObjectInputStream(clientSocket.getInputStream());
                             try {
                               Object objectReceived = infoReceived.readObject();
                               Usuario loginDetails  = (Usuario)objectReceived;
                               System.out.println(loginDetails.username);
                               System.out.println(loginDetails.password);
                               UsuarioDAO usuarioDAO = new UsuarioDAO();
                               if(usuarioDAO.IniciarSesion(loginDetails.username, loginDetails.password))
                               {
                                   //Exito al iniciar sesion
                                   System.out.println("Credenciales validas");
                                   intentosInicioSesion.put(clienteConectado, 0);
                                   DataOutputStream respuestaCredencialesValidas = new DataOutputStream(clientSocket.getOutputStream());
                                   respuestaCredencialesValidas.writeUTF("credenciales_validas");
                                   usuarioDAO.ConectarUsuario(loginDetails.username);
                               }
                               else
                               {
                                   //Hubo un fallo en las credenciales
                                   System.out.println("Credenciales NO validas");
                                   int cantidad_intentos = intentosInicioSesion.get(clienteConectado);
                                   cantidad_intentos ++;
                                   System.out.println("Cantidad intentos: " + cantidad_intentos);
                                   intentosInicioSesion.put(clienteConectado, cantidad_intentos);
                                   if(cantidad_intentos == 3)
                                   {
                                     intentosInicioSesion.put(clienteConectado, 0);
                                     DataOutputStream respuestaRedirigir = new DataOutputStream(clientSocket.getOutputStream());
                                     respuestaRedirigir.writeUTF("redirigir");
                                   }
                                   else
                                   {
                                     DataOutputStream respuestaCredencialesNoValidas = new DataOutputStream(clientSocket.getOutputStream());
                                     respuestaCredencialesNoValidas.writeUTF("credenciales_invalidas");
                                   }

                               }   
                             } catch (ClassNotFoundException ex) {
                                 Logger.getLogger(Coco_Chat_Server.class.getName()).log(Level.SEVERE, null, ex);
                             }
                        }
                        if(funcion.equals("recuperar_cuenta_validar"))
                        {
                             ObjectInputStream infoReceived = new ObjectInputStream(clientSocket.getInputStream());
                             try {
                               Object objectReceived = infoReceived.readObject();
                               Usuario recoverAccountValidate  = (Usuario)objectReceived;
                               System.out.println(recoverAccountValidate.username);
                               System.out.println(recoverAccountValidate.pregunta_respaldo);
                               UsuarioDAO usuarioDAO = new UsuarioDAO();
                               if(usuarioDAO.RecuperarCuentaValidar(recoverAccountValidate.username, recoverAccountValidate.pregunta_respaldo))
                               {
                                   System.out.println("redirigir_recuperar_contrasena");  
                                   String username = recoverAccountValidate.username;
                                   System.out.println(username);
                                   RespuestaRecuperarCuenta respuesta = new RespuestaRecuperarCuenta("redirigir_recuperar_contrasena", username);
                                   ObjectOutputStream respuestaCredencialesCorrectas = new ObjectOutputStream(clientSocket.getOutputStream());
                                   respuestaCredencialesCorrectas.writeObject(respuesta);
                               }
                               else
                               {
                                   System.out.println("error_fallo_pregunta_o_usuario");
                                   DataOutputStream respuestaCredencialesValidas = new DataOutputStream(clientSocket.getOutputStream());
                                   respuestaCredencialesValidas.writeUTF("error_fallo_pregunta_o_usuario");
                               }   
                             } catch (ClassNotFoundException ex) {
                                 Logger.getLogger(Coco_Chat_Server.class.getName()).log(Level.SEVERE, null, ex);
                             }
                        }
                        if(funcion.equals("recuperar_contrasena"))
                        {
                             ObjectInputStream infoReceived = new ObjectInputStream(clientSocket.getInputStream());
                             try {
                               Object objectReceived = infoReceived.readObject();
                               Usuario recoverAccountValidate  = (Usuario)objectReceived;
                               System.out.println(recoverAccountValidate.username);
                               System.out.println(recoverAccountValidate.password);
                               UsuarioDAO usuarioDAO = new UsuarioDAO();
                               if(usuarioDAO.CambiarContrasena(recoverAccountValidate.username, recoverAccountValidate.password))
                               {
                                   //chido
                                   System.out.println("exito_cambiar_contrasena");
                                   DataOutputStream respuestaCredencialesValidas = new DataOutputStream(clientSocket.getOutputStream());
                                   respuestaCredencialesValidas.writeUTF("exito_cambiar_contrasena");
                               }
                               else
                               {
                                   System.out.println("error_cambiar_contrasena");
                                   DataOutputStream respuestaCredencialesValidas = new DataOutputStream(clientSocket.getOutputStream());
                                   respuestaCredencialesValidas.writeUTF("error_cambiar_contrasena");
                               }   
                             } catch (ClassNotFoundException ex) {
                                 Logger.getLogger(Coco_Chat_Server.class.getName()).log(Level.SEVERE, null, ex);
                             }
                        }

                        if(funcion.equals("mostrar_usuarios"))
                        {
                            System.out.println("Mostrar Usuarios");
                            DataInputStream informacionUsuarioCliente = new DataInputStream(clientSocket.getInputStream());
                            String nombre_usuario = informacionUsuarioCliente.readUTF();
                            System.out.println(nombre_usuario);
                            UsuarioDAO usuarioDAO = new UsuarioDAO();
                            ArrayList<Usuario> listaUsuarios = usuarioDAO.obtenerUsuarios(nombre_usuario);
                            ObjectOutputStream respuestaCredencialesCorrectas = new ObjectOutputStream(clientSocket.getOutputStream());
                            respuestaCredencialesCorrectas.writeObject(listaUsuarios);
                        }
                        
                        if(funcion.equals("mostrar_amigos"))
                        {
                            System.out.println("Mostrar Amigos");
                             DataInputStream informacionUsuarioCliente = new DataInputStream(clientSocket.getInputStream());
                             String nombre_usuario = informacionUsuarioCliente.readUTF();
                             System.out.println(nombre_usuario);
                            UsuarioDAO usuarioDAO = new UsuarioDAO();
                            int IDUsuario = usuarioDAO.ObtenerIDUsuario(nombre_usuario);
                            ArrayList<Usuario> listaAmigos = usuarioDAO.obtenerAmigos(IDUsuario);
                            ObjectOutputStream respuestaCredencialesCorrectas = new ObjectOutputStream(clientSocket.getOutputStream());
                            respuestaCredencialesCorrectas.writeObject(listaAmigos);
                        }
                        
                        if(funcion.equals("mostrar_no_amigos"))
                        {
                            System.out.println("Mostrar No Amigos");
                            DataInputStream informacionUsuarioCliente = new DataInputStream(clientSocket.getInputStream());
                            String nombre_usuario = informacionUsuarioCliente.readUTF();
                            System.out.println(nombre_usuario);
                            UsuarioDAO usuarioDAO = new UsuarioDAO();
                            int IDUsuario = usuarioDAO.ObtenerIDUsuario(nombre_usuario);
                            ArrayList<Usuario> listaNoAmigos = usuarioDAO.obtenerNoAmigos(IDUsuario);
                            ObjectOutputStream respuestaCredencialesCorrectas = new ObjectOutputStream(clientSocket.getOutputStream());
                            respuestaCredencialesCorrectas.writeObject(listaNoAmigos);
                        }
                        
                        if(funcion.equals("cargar_usuarios_crear_grupo"))
                        {
                            System.out.println("Cargar usuarios al crear grupo");
                            DataInputStream informacionUsuarioCliente = new DataInputStream(clientSocket.getInputStream());
                            String nombre_usuario = informacionUsuarioCliente.readUTF();
                            System.out.println(nombre_usuario);
                            UsuarioDAO usuarioDAO = new UsuarioDAO();
                            ArrayList<Usuario> listaUsuariosNoPertenecientes = usuarioDAO.obtenerUsuarios(nombre_usuario);
                            ObjectOutputStream respuestaCredencialesCorrectas = new ObjectOutputStream(clientSocket.getOutputStream());
                            respuestaCredencialesCorrectas.writeObject(listaUsuariosNoPertenecientes);
                        }
                        
                        if(funcion.equals("crear_grupo"))
                        {
                            System.out.println("Crear grupo");
                             ObjectInputStream infoReceived = new ObjectInputStream(clientSocket.getInputStream());
                             try {
                               Object objectReceived = infoReceived.readObject();
                               SolicitudCrearGrupo infoCrearGrupo  = (SolicitudCrearGrupo)objectReceived;
                               System.out.println(infoCrearGrupo.creador_grupo);
                               System.out.println("Usuarios Invitados: ");
                                for(String user : infoCrearGrupo.usuarios_invitados)
                                {
                                    System.out.println(user);
                                }
                               UsuarioDAO usuarioDAO = new UsuarioDAO();
                               int IDUsuario = usuarioDAO.ObtenerIDUsuario(infoCrearGrupo.creador_grupo);
                               GruposDAO grupoDAO = new GruposDAO();
                               grupoDAO.CrearGrupo(infoCrearGrupo.nombre_grupo, IDUsuario, infoCrearGrupo.usuarios_invitados);
                             } catch (ClassNotFoundException ex) {
                                 Logger.getLogger(Coco_Chat_Server.class.getName()).log(Level.SEVERE, null, ex);
                             }
                        }

                        if(funcion.equals("mostrar_grupos"))
                        {
                            System.out.println("Mostrar Grupos");
                            DataInputStream informacionUsuarioCliente = new DataInputStream(clientSocket.getInputStream());
                            String nombre_usuario = informacionUsuarioCliente.readUTF();
                            System.out.println(nombre_usuario);
                            UsuarioDAO usuarioDAO = new UsuarioDAO();
                            int IDUsuario = usuarioDAO.ObtenerIDUsuario(nombre_usuario);
                            ArrayList<Grupo> listaGrupos = usuarioDAO.obtenerGrupos(IDUsuario);
                            ObjectOutputStream respuestaCredencialesCorrectas = new ObjectOutputStream(clientSocket.getOutputStream());
                            respuestaCredencialesCorrectas.writeObject(listaGrupos);
                        }
                        
                        if(funcion.equals("mostrar_grupos_propietario"))
                        {
                            System.out.println("Mostrar Grupos Propietario");
                            DataInputStream informacionUsuarioCliente = new DataInputStream(clientSocket.getInputStream());
                            String nombre_usuario = informacionUsuarioCliente.readUTF();
                            System.out.println(nombre_usuario);
                            UsuarioDAO usuarioDAO = new UsuarioDAO();
                            int IDUsuario = usuarioDAO.ObtenerIDUsuario(nombre_usuario);
                            ArrayList<Grupo> listaGruposPropietario = usuarioDAO.obtenerGruposPropietario(IDUsuario);
                            ObjectOutputStream respuestaGruposPropietario = new ObjectOutputStream(clientSocket.getOutputStream());
                            respuestaGruposPropietario.writeObject(listaGruposPropietario);
                        }
                        
                        if(funcion.equals("mostrar_no_miembros"))
                        {
                            System.out.println("Mostrar No Miembros");
                             ObjectInputStream infoReceived = new ObjectInputStream(clientSocket.getInputStream());
                             try {
                               Object objectReceived = infoReceived.readObject();
                               SolicitarUsuariosNoMiembros infoCrearGrupo  = (SolicitarUsuariosNoMiembros)objectReceived;
                               System.out.println("Usuario: " + infoCrearGrupo.UsuarioLoggeado);
                               System.out.println("Grupo: " + infoCrearGrupo.NombreGrupo);
                               UsuarioDAO usuarioDAO = new UsuarioDAO();
                               GruposDAO grupoDAO = new GruposDAO();
                               int IDUsuario = usuarioDAO.ObtenerIDUsuario(infoCrearGrupo.UsuarioLoggeado);
                               int IDGrupo = grupoDAO.ObtenerIDGrupo(infoCrearGrupo.NombreGrupo);
                               ArrayList<Usuario> listaUsuariosNoMiembros = grupoDAO.obtenerNoMiembrosSinInvitacion(IDGrupo, IDUsuario);
                               ObjectOutputStream respuestaUsuariosNoMiembros = new ObjectOutputStream(clientSocket.getOutputStream());
                               respuestaUsuariosNoMiembros.writeObject(listaUsuariosNoMiembros);
                             } catch (ClassNotFoundException ex) {
                                 Logger.getLogger(Coco_Chat_Server.class.getName()).log(Level.SEVERE, null, ex);
                             }
                        }
                        
                        if(funcion.equals("cerrar_sesion"))
                        {
                               System.out.println("Desconectar usuario:");
                               DataInputStream informacionUsuarioCliente = new DataInputStream(clientSocket.getInputStream());
                               String nombre_usuario = informacionUsuarioCliente.readUTF();
                               System.out.println(nombre_usuario);
                               UsuarioDAO usuarioDAO = new UsuarioDAO();
                               MensajesDAO mensajesDAO = new MensajesDAO();
                               int ID_usuario = usuarioDAO.ObtenerIDUsuario(nombre_usuario);
                               mensajesDAO.EliminarMensajeUsuario(ID_usuario);
                               usuarioDAO.DesconectarUsuario(nombre_usuario);
                               clientSocket.close();
                        }
                        
                        if(funcion.equals("enviar_mensaje_usuario"))
                        {
                               System.out.println("Enviar mensajes usuario:");
                               ObjectInputStream infoReceived = new ObjectInputStream(clientSocket.getInputStream());
                               Object objectReceived;
                           try {
                               objectReceived = infoReceived.readObject();
                               EnviarMensajesUsuario enviarMensajeUsuario  = (EnviarMensajesUsuario)objectReceived;
                               UsuarioDAO usuarioDAO = new UsuarioDAO();
                               MensajesDAO mensajesDAO = new MensajesDAO();
                               int ID_usuario_loggeado = usuarioDAO.ObtenerIDUsuario(enviarMensajeUsuario.remitente);
                               int ID_usuario_seleccionado = usuarioDAO.ObtenerIDUsuario(enviarMensajeUsuario.destinatario);
                               String mensaje_enviado = enviarMensajeUsuario.mensaje;
                               mensajesDAO.EnviarMensajeUsuario(ID_usuario_loggeado, ID_usuario_seleccionado, mensaje_enviado);
                               
//                               ArrayList<RespuestaMensajesUsuario> mensajes_usuario = mensajesDAO.obtenerMensajesUsuario(ID_usuario_loggeado, ID_usuario_seleccionado);
//                               ObjectOutputStream respuestaMensajesUsuario = new ObjectOutputStream(clientSocket.getOutputStream());
//                               
//                               respuestaMensajesUsuario.writeObject(mensajes_usuario);
                               
                           } catch (ClassNotFoundException ex) {
                               Logger.getLogger(Coco_Chat_Server.class.getName()).log(Level.SEVERE, null, ex);
                           }
                        }
                        
                        if(funcion.equals("cargar_mensajes_usuario"))
                        {
                               System.out.println("Cargar mensajes usuario:");
                               ObjectInputStream infoReceived = new ObjectInputStream(clientSocket.getInputStream());
                               Object objectReceived;
                           try {
                               objectReceived = infoReceived.readObject();
                               SolicitarMensajesUsuario usuariosMensaje  = (SolicitarMensajesUsuario)objectReceived;
                               
                               System.out.println("Remitente: " + usuariosMensaje.usuarioLoggeado);
                               System.out.println("Destinatario: " + usuariosMensaje.usuarioSeleccionado);
                               UsuarioDAO usuarioDAO = new UsuarioDAO();
                               MensajesDAO mensajesDAO = new MensajesDAO();
                               
                               int ID_usuario_loggeado = usuarioDAO.ObtenerIDUsuario(usuariosMensaje.usuarioLoggeado);
                               int ID_usuario_seleccionado = usuarioDAO.ObtenerIDUsuario(usuariosMensaje.usuarioSeleccionado);
                               
                               ArrayList<RespuestaMensajesUsuario> mensajes_usuario = mensajesDAO.obtenerMensajesUsuario(ID_usuario_loggeado, ID_usuario_seleccionado);
                               ObjectOutputStream respuestaMensajesUsuario = new ObjectOutputStream(clientSocket.getOutputStream());
                               
                               respuestaMensajesUsuario.writeObject(mensajes_usuario);
                               
                           } catch (ClassNotFoundException ex) {
                               Logger.getLogger(Coco_Chat_Server.class.getName()).log(Level.SEVERE, null, ex);
                           }
                        }
                  
                        if(funcion.equals("enviar_mensaje_amigo"))
                        {
                               System.out.println("Enviar mensajes amigo:");
                               ObjectInputStream infoReceived = new ObjectInputStream(clientSocket.getInputStream());
                               Object objectReceived;
                           try {
                               objectReceived = infoReceived.readObject();
                               EnviarMensajesUsuario enviarMensajeAmigo  = (EnviarMensajesUsuario)objectReceived;
                               UsuarioDAO usuarioDAO = new UsuarioDAO();
                               MensajesDAO mensajesDAO = new MensajesDAO();
                               int ID_usuario_loggeado = usuarioDAO.ObtenerIDUsuario(enviarMensajeAmigo.remitente);
                               int ID_usuario_seleccionado = usuarioDAO.ObtenerIDUsuario(enviarMensajeAmigo.destinatario);
                               String mensaje_enviado = enviarMensajeAmigo.mensaje;
                               mensajesDAO.EnviarMensajeAmigo(ID_usuario_loggeado, ID_usuario_seleccionado, mensaje_enviado);
                               
//                               ArrayList<RespuestaMensajesAmigo> mensajes_usuario = mensajesDAO.obtenerMensajesAmigo(ID_usuario_loggeado, ID_usuario_seleccionado);
//                               ObjectOutputStream respuestaMensajesAmigo = new ObjectOutputStream(clientSocket.getOutputStream());
//                               
//                               respuestaMensajesAmigo.writeObject(mensajes_usuario);
                           } catch (ClassNotFoundException ex) {
                               Logger.getLogger(Coco_Chat_Server.class.getName()).log(Level.SEVERE, null, ex);
                           }
                        }
                        
                        if(funcion.equals("cargar_mensajes_amigo"))
                        {
                               System.out.println("Cargar mensajes amigo:");
                               ObjectInputStream infoReceived = new ObjectInputStream(clientSocket.getInputStream());
                               Object objectReceived;
                           try {
                               objectReceived = infoReceived.readObject();
                               SolicitarMensajesUsuario amigosMensaje  = (SolicitarMensajesUsuario)objectReceived;
                               
                               System.out.println("Remitente: " + amigosMensaje.usuarioLoggeado);
                               System.out.println("Destinatario: " + amigosMensaje.usuarioSeleccionado);
                               UsuarioDAO usuarioDAO = new UsuarioDAO();
                               MensajesDAO mensajesDAO = new MensajesDAO();
                               
                               int ID_usuario_loggeado = usuarioDAO.ObtenerIDUsuario(amigosMensaje.usuarioLoggeado);
                               int ID_usuario_seleccionado = usuarioDAO.ObtenerIDUsuario(amigosMensaje.usuarioSeleccionado);
                               
//                               ArrayList<RespuestaMensajesAmigo> mensajes_amigo = mensajesDAO.obtenerMensajesAmigo(ID_usuario_loggeado, ID_usuario_seleccionado);
//                               ObjectOutputStream respuestaMensajesAmigo = new ObjectOutputStream(clientSocket.getOutputStream());
//                               
//                               respuestaMensajesAmigo.writeObject(mensajes_amigo);
                               
                           } catch (ClassNotFoundException ex) {
                               Logger.getLogger(Coco_Chat_Server.class.getName()).log(Level.SEVERE, null, ex);
                           }
                        }
                        
                        if(funcion.equals("enviar_mensaje_grupo"))
                        {
                               System.out.println("Enviar mensajes grupo:");
                               ObjectInputStream infoReceived = new ObjectInputStream(clientSocket.getInputStream());
                               Object objectReceived;
                           try {
                               objectReceived = infoReceived.readObject();
                               EnviarMensajesUsuario enviarMensajeGrupo  = (EnviarMensajesUsuario)objectReceived;
                               UsuarioDAO usuarioDAO = new UsuarioDAO();
                               MensajesDAO mensajesDAO = new MensajesDAO();
                               GruposDAO gruposDAO = new GruposDAO();
                               int ID_usuario_loggeado = usuarioDAO.ObtenerIDUsuario(enviarMensajeGrupo.remitente);
                               int ID_grupo = gruposDAO.ObtenerIDGrupo(enviarMensajeGrupo.destinatario);
                               String mensaje_enviado = enviarMensajeGrupo.mensaje;
                               mensajesDAO.EnviarMensajeGrupo(ID_usuario_loggeado, ID_grupo, mensaje_enviado);
                               
                               ArrayList<RespuestaMensajesGrupo> mensajes_grupo = mensajesDAO.obtenerMensajesGrupo(ID_grupo);
                               ObjectOutputStream respuestaMensajesGrupo = new ObjectOutputStream(clientSocket.getOutputStream());
                               
                               respuestaMensajesGrupo.writeObject(mensajes_grupo);
                           } catch (ClassNotFoundException ex) {
                               Logger.getLogger(Coco_Chat_Server.class.getName()).log(Level.SEVERE, null, ex);
                           }
                        }
                        if(funcion.equals("cargar_mensajes_grupo"))
                        {
                               System.out.println("Cargar mensajes grupo:");
                               ObjectInputStream infoReceived = new ObjectInputStream(clientSocket.getInputStream());
                               Object objectReceived;
                           try {
                               objectReceived = infoReceived.readObject();
                               SolicitarMensajesUsuario grupoMensajes  = (SolicitarMensajesUsuario)objectReceived;
                               
                               System.out.println("Remitente: " + grupoMensajes.usuarioLoggeado);
                               System.out.println("Destinatario: " + grupoMensajes.usuarioSeleccionado);
                               GruposDAO grupoDAO = new GruposDAO();
                               MensajesDAO mensajesDAO = new MensajesDAO();
                               
                               int ID_grupo = grupoDAO.ObtenerIDGrupo(grupoMensajes.usuarioSeleccionado);
                               
                               ArrayList<RespuestaMensajesGrupo> mensajes_grupo = mensajesDAO.obtenerMensajesGrupo(ID_grupo);
                               ObjectOutputStream respuestaMensajesGrupo = new ObjectOutputStream(clientSocket.getOutputStream());
                               
                               respuestaMensajesGrupo.writeObject(mensajes_grupo);
                               
                           } catch (ClassNotFoundException ex) {
                               Logger.getLogger(Coco_Chat_Server.class.getName()).log(Level.SEVERE, null, ex);
                           }
                        }
                        
                        if(funcion.equals("enviar_solicitud_amistad"))
                        {
                               System.out.println("Enviar solicitud amistad:");
                               ObjectInputStream infoReceived = new ObjectInputStream(clientSocket.getInputStream());
                               Object objectReceived;
                           try {
                               objectReceived = infoReceived.readObject();
                               ArrayList<InfoSolicitudAmistad> enviarSolicitudAmistad  = (ArrayList<InfoSolicitudAmistad>)objectReceived;
                               UsuarioDAO usuarioDAO = new UsuarioDAO();
                               SolicitudDAO solicitudDAO = new SolicitudDAO();
                                                              
                                for(InfoSolicitudAmistad solicitudAmistad : enviarSolicitudAmistad)
                                {
                                    int ID_usuario_remitente = usuarioDAO.ObtenerIDUsuario(solicitudAmistad.remitente_solicitud_amistad);
                                    int ID_usuario_destinatario = usuarioDAO.ObtenerIDUsuario(solicitudAmistad.destinatario_solicitud_amistad);
                                    solicitudDAO.EnviarSolicitudAmistad(ID_usuario_remitente, ID_usuario_destinatario);
                                }

                           } catch (ClassNotFoundException ex) {
                               Logger.getLogger(Coco_Chat_Server.class.getName()).log(Level.SEVERE, null, ex);
                           }
                        }
                        
                        if(funcion.equals("ver_solicitudes_amistad"))
                        {
                            System.out.println("Ver solicitudes amistad");
                            DataInputStream informacionUsuarioCliente = new DataInputStream(clientSocket.getInputStream());
                            String nombre_usuario = informacionUsuarioCliente.readUTF();
                            System.out.println(nombre_usuario);
                            UsuarioDAO usuarioDAO = new UsuarioDAO();
                            int IDUsuario = usuarioDAO.ObtenerIDUsuario(nombre_usuario);
                            ArrayList<InfoSolicitudAmistad> listaSolicitudesAmistad = usuarioDAO.obtenerSolicitudesAmistad(IDUsuario);
                            ObjectOutputStream respuestaCredencialesCorrectas = new ObjectOutputStream(clientSocket.getOutputStream());
                            respuestaCredencialesCorrectas.writeObject(listaSolicitudesAmistad);
                        }

                        if(funcion.equals("aceptar_solicitud_amistad"))
                        {
                               System.out.println("Aceptar solicitud amistad:");
                               ObjectInputStream infoReceived = new ObjectInputStream(clientSocket.getInputStream());
                               Object objectReceived;
                           try {
                               objectReceived = infoReceived.readObject();
                               ArrayList<InfoSolicitudAmistad> infoSolicitudAceptada  = (ArrayList<InfoSolicitudAmistad>)objectReceived;
                               UsuarioDAO usuarioDAO = new UsuarioDAO();
                               SolicitudDAO solicitudDAO = new SolicitudDAO();
                               
                                for(InfoSolicitudAmistad solicitudAceptada : infoSolicitudAceptada)
                                {
                                    System.out.println("Remitente solicitud amistad: " + solicitudAceptada.remitente_solicitud_amistad);
                                    System.out.println("Destinatario solicitud amistad:" + solicitudAceptada.destinatario_solicitud_amistad);
                                    int ID_usuario_remitente = usuarioDAO.ObtenerIDUsuario(solicitudAceptada.remitente_solicitud_amistad);
                                    int ID_usuario_destinatario = usuarioDAO.ObtenerIDUsuario(solicitudAceptada.destinatario_solicitud_amistad);
                                    System.out.println("Remitente solicitud amistad: " + ID_usuario_remitente);
                                    System.out.println("Destinatario solicitud amistad:" + ID_usuario_destinatario);
                                    solicitudDAO.AceptarSolicitudAmistad(ID_usuario_remitente, ID_usuario_destinatario);
                                }
                           } catch (ClassNotFoundException ex) {
                               Logger.getLogger(Coco_Chat_Server.class.getName()).log(Level.SEVERE, null, ex);
                           }
                        }
                        if(funcion.equals("rechazar_solicitud_amistad"))
                        {
                               System.out.println("Rechazar solicitud amistad:");
                               ObjectInputStream infoReceived = new ObjectInputStream(clientSocket.getInputStream());
                               Object objectReceived;
                           try {
                               objectReceived = infoReceived.readObject();
                               ArrayList<InfoSolicitudAmistad> infoSolicitudRechazada  = (ArrayList<InfoSolicitudAmistad>)objectReceived;
                               UsuarioDAO usuarioDAO = new UsuarioDAO();
                               SolicitudDAO solicitudDAO = new SolicitudDAO();
                               
                                for(InfoSolicitudAmistad solicitudRechazada : infoSolicitudRechazada)
                                {
                                    int ID_usuario_remitente = usuarioDAO.ObtenerIDUsuario(solicitudRechazada.remitente_solicitud_amistad);
                                    int ID_usuario_destinatario = usuarioDAO.ObtenerIDUsuario(solicitudRechazada.destinatario_solicitud_amistad);
                                    solicitudDAO.RechazarSolicitudAmistad(ID_usuario_remitente, ID_usuario_destinatario);
                                }
                           } catch (ClassNotFoundException ex) {
                               Logger.getLogger(Coco_Chat_Server.class.getName()).log(Level.SEVERE, null, ex);
                           }
                        }
                            
                        if(funcion.equals("enviar_invitacion_grupo"))
                        {
                               System.out.println("Enviar invitacion grupo:");
                               ObjectInputStream infoReceived = new ObjectInputStream(clientSocket.getInputStream());
                               Object objectReceived;
                           try {
                               objectReceived = infoReceived.readObject();
                               ArrayList<InfoInvitacionGrupo> enviarSolicitudGrupo = (ArrayList<InfoInvitacionGrupo>)objectReceived;
                               UsuarioDAO usuarioDAO = new UsuarioDAO();
                               GruposDAO gruposDAO = new GruposDAO();
                               
                                for(InfoInvitacionGrupo solicitudGrupo : enviarSolicitudGrupo)
                                {
                                int ID_grupo = gruposDAO.ObtenerIDGrupo(solicitudGrupo.grupo_invitado);
                                int ID_usuario_remitente = usuarioDAO.ObtenerIDUsuario(solicitudGrupo.remitente_invitacion_grupo);
                                int ID_usuario_destinatario = usuarioDAO.ObtenerIDUsuario(solicitudGrupo.destinatario_invitacion_grupo);
                               gruposDAO.EnviarInvitacionGrupo(ID_grupo, ID_usuario_remitente, ID_usuario_destinatario);
                                }

                           } catch (ClassNotFoundException ex) {
                               Logger.getLogger(Coco_Chat_Server.class.getName()).log(Level.SEVERE, null, ex);
                           }
                        }
                        
                        if(funcion.equals("ver_invitaciones_grupo"))
                        {
                            System.out.println("Ver invitaciones grupo");
                            DataInputStream informacionUsuarioCliente = new DataInputStream(clientSocket.getInputStream());
                            String nombre_usuario = informacionUsuarioCliente.readUTF();
                            System.out.println(nombre_usuario);
                            UsuarioDAO usuarioDAO = new UsuarioDAO();
                            int IDUsuario = usuarioDAO.ObtenerIDUsuario(nombre_usuario);
                            ArrayList<InfoInvitacionGrupo> listaInvitacionesGrupo = usuarioDAO.obtenerInvitacionesGrupos(IDUsuario);
                            ObjectOutputStream respuestaCredencialesCorrectas = new ObjectOutputStream(clientSocket.getOutputStream());
                            respuestaCredencialesCorrectas.writeObject(listaInvitacionesGrupo);
                        }
                        
                        if(funcion.equals("aceptar_invitacion_grupo"))
                        {
                               System.out.println("Aceptar invitacion grupo:");
                               ObjectInputStream infoReceived = new ObjectInputStream(clientSocket.getInputStream());
                               Object objectReceived;
                           try {
                               objectReceived = infoReceived.readObject();
                               ArrayList<InfoInvitacionGrupo> infoInvitacionAceptada  = (ArrayList<InfoInvitacionGrupo>)objectReceived;
                               UsuarioDAO usuarioDAO = new UsuarioDAO();
                               GruposDAO gruposDAO = new GruposDAO();
                               
                                for(InfoInvitacionGrupo invitacionAceptada : infoInvitacionAceptada)
                                {
                                    int ID_usuario_destinatario = usuarioDAO.ObtenerIDUsuario(invitacionAceptada.destinatario_invitacion_grupo);
                                    int ID_grupo = gruposDAO.ObtenerIDGrupo(invitacionAceptada.grupo_invitado);

                                    gruposDAO.AceptarInvitacionGrupo(ID_grupo, ID_usuario_destinatario);
                                }
                           } catch (ClassNotFoundException ex) {
                               Logger.getLogger(Coco_Chat_Server.class.getName()).log(Level.SEVERE, null, ex);
                           }
                        }
                        if(funcion.equals("rechazar_invitacion_grupo"))
                        {
                               System.out.println("Rechazar invitacion grupo:");
                               ObjectInputStream infoReceived = new ObjectInputStream(clientSocket.getInputStream());
                               Object objectReceived;
                           try {
                               objectReceived = infoReceived.readObject();
                               ArrayList<InfoInvitacionGrupo> infoInvitacionRechazada  = (ArrayList<InfoInvitacionGrupo>)objectReceived;
                               UsuarioDAO usuarioDAO = new UsuarioDAO();
                               GruposDAO gruposDAO = new GruposDAO();
                               
                                for(InfoInvitacionGrupo invitacionRechazada : infoInvitacionRechazada)
                                {
                                    int ID_usuario_destinatario = usuarioDAO.ObtenerIDUsuario(invitacionRechazada.destinatario_invitacion_grupo);
                                    int ID_grupo = gruposDAO.ObtenerIDGrupo(invitacionRechazada.grupo_invitado);

                                    gruposDAO.RechazarInvitacionGrupo(ID_grupo, ID_usuario_destinatario);
                                }
                           } catch (ClassNotFoundException ex) {
                               Logger.getLogger(Coco_Chat_Server.class.getName()).log(Level.SEVERE, null, ex);
                           }
                        }
                        
                        if(funcion.equals("salir_grupo"))
                        {
                             System.out.println("Salir grupo");
                             ObjectInputStream infoReceived = new ObjectInputStream(clientSocket.getInputStream());
                             try {
                               Object objectReceived = infoReceived.readObject();
                               ArrayList <SolicitarInfoGrupo> infoGrupo  = (ArrayList <SolicitarInfoGrupo>)objectReceived;

                               UsuarioDAO usuarioDAO = new UsuarioDAO();
                               GruposDAO grupoDAO = new GruposDAO();
                                                              
                                for(SolicitarInfoGrupo grupo : infoGrupo)
                                {
                               System.out.println(grupo.UsuarioLoggeado);
                               System.out.println(grupo.NombreGrupo);
                                    int ID_Usuario_Salir = usuarioDAO.ObtenerIDUsuario(grupo.UsuarioLoggeado);
                                    int ID_Grupo_Salir = grupoDAO.ObtenerIDGrupo(grupo.NombreGrupo);
                                    grupoDAO.SalirGrupo(ID_Grupo_Salir, ID_Usuario_Salir);
                                }
                             } catch (ClassNotFoundException ex) {
                                 Logger.getLogger(Coco_Chat_Server.class.getName()).log(Level.SEVERE, null, ex);
                             }
                        }
                        
                        if(funcion.equals("eliminar_grupo"))
                        {
                             System.out.println("Eliminar grupo");
                             ObjectInputStream infoReceived = new ObjectInputStream(clientSocket.getInputStream());
                             try {
                               Object objectReceived = infoReceived.readObject();
                               ArrayList <SolicitarInfoGrupo> infoGrupo  = (ArrayList <SolicitarInfoGrupo>)objectReceived;

                               GruposDAO grupoDAO = new GruposDAO();
                                                              
                                for(SolicitarInfoGrupo grupo : infoGrupo)
                                {
                                    System.out.println(grupo.NombreGrupo);
                                    int ID_Grupo_Eliminar = grupoDAO.ObtenerIDGrupo(grupo.NombreGrupo);
                                    grupoDAO.EliminarGrupo(ID_Grupo_Eliminar);
                                }
                             } catch (ClassNotFoundException ex) {
                                 Logger.getLogger(Coco_Chat_Server.class.getName()).log(Level.SEVERE, null, ex);
                             }
                        }
                       
                   }
                   catch(IOException e){
                       e.printStackTrace();
                   }finally{
                       try{
                           clientSocket.close();
                       }catch(IOException e){
                           e.printStackTrace();
                       }
                   }
               });
               clienteThread.start();

            } //Fin del while

        } catch (IOException ex) {
            Logger.getLogger(Socket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}