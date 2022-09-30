package icai.dtc.isw.ui;

import icai.dtc.isw.dao.BDUsuarios;
import icai.dtc.isw.dao.ConnectionDAO;
import icai.dtc.isw.domain.Cliente;
import icai.dtc.isw.domain.Decoracion;
import icai.dtc.isw.controler.GestorClientes;

import javax.swing.*;
import java.awt.*;

public class JVentana extends JFrame {

    private GridBagConstraints gbc = new GridBagConstraints();
    private GestorClientes gClientes = new GestorClientes(new BDUsuarios(ConnectionDAO.getInstance().getConnection()));
    public static final Color COLOR_BACKGROUND = new Color(51, 46, 46);
    public static final Color COLOR_LBL = new Color(190, 176, 176);
    private static final Color COLOR_BTN = new Color(43, 131, 120);

    public JVentana(){
        super("MovieGram");

        JPanel pnlCentral = new JPanel(new GridLayout(2,1));


        //Logo
        JPanel pnlLogo = new JPanel();
        pnlLogo.setBackground(COLOR_BACKGROUND);

        JLabel lblLogo = new JLabel();
        lblLogo.setIcon(new ImageIcon(new ImageIcon("src/main/resources/logo.png").getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT)));
        pnlLogo.add(lblLogo);

        //Inicio
        CardLayout cards = new CardLayout();
        JPanel pnlInicio = new JPanel(cards);


        //Inicio Sesión
        JPanel pnlInicioSesion = new JPanel(new GridBagLayout());
        pnlInicioSesion.setBackground(COLOR_BACKGROUND);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        setGbc(0,0,2,1);
        JLabel lblUsuario = new JLabel("Usuario ");
        lblUsuario.setForeground(COLOR_LBL);
        pnlInicioSesion.add(lblUsuario, gbc);

        setGbc(2,0,2,1);
        Texto txtUsuario = new Texto(15, Decoracion.DECORADO, COLOR_LBL, COLOR_BACKGROUND);
        pnlInicioSesion.add(txtUsuario, gbc);

        setGbc(0,1,2,1);
        JLabel lblContra = new JLabel("Contraseña ");
        lblContra.setForeground(COLOR_LBL);
        pnlInicioSesion.add(lblContra, gbc);

        setGbc(2,1,2,1);
        Texto pswContra = new Texto(15, Decoracion.DECORADO, COLOR_LBL, COLOR_BACKGROUND);
        pnlInicioSesion.add(pswContra, gbc);

        setGbc(0, 2, 4, 1);
        JButton btnNuevoUser = new JButton("Registrarse");
        btnNuevoUser.setContentAreaFilled(false);
        btnNuevoUser.setBorderPainted(false);
        btnNuevoUser.setForeground(COLOR_BTN);
        pnlInicioSesion.add(btnNuevoUser, gbc);
        btnNuevoUser.addActionListener(e -> cards.show(pnlInicio, "registro"));

        setGbc(0,3,4,1);
        JButton btnContraOlvidada = new JButton("Recuperar contraseña");
        btnContraOlvidada.setContentAreaFilled(false);
        btnContraOlvidada.setBorderPainted(false);
        btnContraOlvidada.setForeground(COLOR_BTN);
        pnlInicioSesion.add(btnContraOlvidada, gbc);
        btnContraOlvidada.addActionListener(e -> cards.show(pnlInicio, "recuperar"));

        setGbc(0,4,4,1);
        JButton btnLogin = new JButton("Iniciar sesión");
        btnLogin.setContentAreaFilled(false);
        btnLogin.setBorderPainted(false);
        btnLogin.setForeground(COLOR_BTN);
        pnlInicioSesion.add(btnLogin, gbc);
        btnLogin.addActionListener(e -> {
            if (gClientes.getClientes().contains(new Cliente(txtUsuario.getText(), pswContra.getText()))){
                Cliente cliente = gClientes.getBdUsuarios().getCliente(txtUsuario.getText());
                System.out.println("---INICIO SESIÓN---\n" + cliente);
            }else {
                JOptionPane.showMessageDialog(this, "Usuario no encontrado");
            }
        });


        //Registrarse
        JPanel pnlRegistro = new JPanel(new GridBagLayout());
        pnlRegistro.setBackground(COLOR_BACKGROUND);

        setGbc(0,0,2,1);
        JLabel lblNuevoUsuario = new JLabel("Usuario ");
        lblNuevoUsuario.setForeground(COLOR_LBL);
        pnlRegistro.add(lblNuevoUsuario, gbc);

        setGbc(2,0,2,1);
        Texto txtNuevoUsuario = new Texto(15, Decoracion.DECORADO, COLOR_LBL, COLOR_BACKGROUND);
        pnlRegistro.add(txtNuevoUsuario, gbc);

        setGbc(0,1,2,1);
        JLabel lblNuevoCorreo = new JLabel("Correo ");
        lblNuevoCorreo.setForeground(COLOR_LBL);
        pnlRegistro.add(lblNuevoCorreo, gbc);

        setGbc(2,1,2,1);
        Texto txtCorreoNuevo = new Texto(15, Decoracion.DECORADO, COLOR_LBL, COLOR_BACKGROUND);
        pnlRegistro.add(txtCorreoNuevo, gbc);

        setGbc(0,2,2,1);
        JLabel lblNuevaContra = new JLabel("Contraseña ");
        lblNuevaContra.setForeground(COLOR_LBL);
        pnlRegistro.add(lblNuevaContra, gbc);

        setGbc(2,2,2,1);
        Texto pswContraNueva = new Texto(15, Decoracion.DECORADO, COLOR_LBL, COLOR_BACKGROUND);
        pnlRegistro.add(pswContraNueva, gbc);

        setGbc(0, 3, 4, 1);
        JButton btnRegistro = new JButton("Iniciar sesion");
        btnRegistro.setContentAreaFilled(false);
        btnRegistro.setBorderPainted(false);
        btnRegistro.setForeground(COLOR_BTN);
        pnlRegistro.add(btnRegistro, gbc);
        btnRegistro.addActionListener(e -> {
            gClientes.addCliente(new Cliente(txtNuevoUsuario.getText(), pswContraNueva.getText(), txtCorreoNuevo.getText()));
            gClientes.getBdUsuarios().refreshConnection();
        });

        setGbc(0,4,4,1);
        JButton btnVolver = new JButton("Volver");
        btnVolver.setContentAreaFilled(false);
        btnVolver.setBorderPainted(false);
        btnVolver.setForeground(COLOR_BTN);
        pnlRegistro.add(btnVolver, gbc);
        btnVolver.addActionListener(e -> cards.show(pnlInicio, "inicioSesion"));

        //Recuperar contraseña
        JPanel pnlRecuperacion = new JPanel(new GridBagLayout());
        pnlRecuperacion.setBackground(COLOR_BACKGROUND);

        setGbc(0,0,2,1);
        JLabel lblCorreoParaRecuperarContra = new JLabel("Correo ");
        lblCorreoParaRecuperarContra.setForeground(COLOR_LBL);
        pnlRecuperacion.add(lblCorreoParaRecuperarContra, gbc);

        setGbc(2,0,2,1);
        Texto txtCorreoParaRecuperarContra = new Texto(15, Decoracion.DECORADO, COLOR_LBL, COLOR_BACKGROUND);
        pnlRecuperacion.add(txtCorreoParaRecuperarContra, gbc);

        setGbc(0,1,4,1);
        JButton btnRecuperarContra = new JButton("Recuperar");
        btnRecuperarContra.setContentAreaFilled(false);
        btnRecuperarContra.setBorderPainted(false);
        btnRecuperarContra.setForeground(COLOR_BTN);
        pnlRecuperacion.add(btnRecuperarContra, gbc);


        pnlInicio.add(pnlInicioSesion, "inicioSesion");
        cards.show(pnlInicio, "inicioSesion");

        pnlInicio.add(pnlRegistro, "registro");
        pnlInicio.add(pnlRecuperacion, "recuperar");


        //JFrame
        pnlCentral.add(pnlLogo);
        pnlCentral.add(pnlInicio);

        add(pnlCentral);


        setVisible(true);
        setSize(500,600);
        setLocation(500,100);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void setGbc(int gridX, int gridY, int gridWidth, int gridHeight){
        gbc.gridheight = gridHeight;
        gbc.gridwidth = gridWidth;
        gbc.gridx = gridX;
        gbc.gridy = gridY;
    }

    public static void main(String[] args) {
        new JVentana();
    }
}
