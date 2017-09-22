package client;

import static client.Client.alPosts;
import static client.Client.nPostsCount;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.Dimension;
import java.util.ArrayList;
import java.awt.GridBagLayout;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import java.util.Random;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.awt.Font;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import java.awt.GridBagConstraints;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.awt.Event.*;

public class GUI extends javax.swing.JFrame {

    private static final String PASSWORD = "PASS";

    public static ArrayList<JPanel> alPostsPanels;

    public static ArrayList<JPanel> alPrevPostsPanels;

    private static Random rand = new Random();

    private static final String sFilePath
            = "/home/vm/Documents/DevelopmentWorkspaces/NetBeansWorkspace/Client/Client/src/data.txt";

    // создаем экземпляр нашего класса который мы создали в первом шаге
    private static InputOutputFileStream fileStream;

    private static final Font titleFont = new Font(Font.SERIF, 40, 40);

    private static final Font descriptionFont = new Font(Font.SERIF, 20, 20);

    public GUI() {

        fileStream = new InputOutputFileStream(sFilePath);

        bAlreadySendM = false;

//        try {
//            fileStream.read();
//        } catch (IOException e) {
//        }
        alPostsPanels = new ArrayList<JPanel>();

        alPrevPostsPanels = new ArrayList<JPanel>();

        GeneratePostsPanels();

        this.setTitle("Daily Poster");

        initComponents();

        jPanel = new JPanel();

        backToTheMainGUI(jPanel);
    }

    private void GeneratePostsPanels() {
        //read from file data and generate alPosts

        //generate JPanels from alPosts
    }

    private JScrollPane jScrollPaneModer;
    private JButton btnBackToMainFromPrev;

    private void toModer() {
        jPanel.removeAll();
        jScrollPaneModer = new javax.swing.JScrollPane();
        btnBackToMainFromPrev = new javax.swing.JButton();
        btnBackToMainFromPrev.setVisible(true);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(800, 500));
        setMinimumSize(new java.awt.Dimension(800, 500));
        setPreferredSize(new java.awt.Dimension(850, 700));
        setResizable(false);

        labelMainTitle = new javax.swing.JLabel();
        panelAllPosts = new javax.swing.JPanel();
        btnAddNewPost = new javax.swing.JButton();
        scrollAllPosts = new javax.swing.JScrollPane(panelAllPosts);
        textModer = new javax.swing.JTextField();

        final JScrollPane scrollPane = new JScrollPane(panelAllPosts);
        panelAllPosts.setLayout(new BoxLayout(panelAllPosts, BoxLayout.Y_AXIS));

        scrollAllPosts.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        JPanel jp;
        JLabel id;
        JLabel labelTitle;
        JTextArea labelDescription;

        for (int i = 0; i < Client.prevPosts.size(); ++i) {

            if (Client.prevPosts.get(i).isToPublish()) {

                jp = new JPanel();
                jp.setLayout(new GridBagLayout());
                jp.setPreferredSize(new Dimension(600, 200));
                jp.setMaximumSize(new Dimension(600, 200));
                jp.setBackground(new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255)));
                jp.setAlignmentX(JPanel.CENTER_ALIGNMENT);

                id = new JLabel();
                id.setName("PostID");
                id.setText(Integer.toString(i));
                id.setVisible(false);

                labelTitle = new JLabel();
                labelTitle.setFont(titleFont);
                labelTitle.setText(Client.prevPosts.get(i).getTitle());

                labelDescription = new JTextArea();
                labelDescription.setEditable(false);
                labelDescription.setBackground(jp.getBackground());
                labelDescription.setText(Client.prevPosts.get(i).getShortDescriptionText());
                labelDescription.setFont(descriptionFont);

                GridBagConstraints gbc = new GridBagConstraints();

                jp.add(id);

                gbc.gridx = 0;
                gbc.gridy = 0;
                //gbc.gridwidth = 2;

                jp.add(labelTitle, gbc);

                gbc.gridx = 0;
                gbc.gridy = 1;
                //gbc.gridwidth = 2;

                jp.add(labelDescription, gbc);
                alPostsPanels.add(jp);
                panelAllPosts.add(jp);
                final String s = id.getText();
                jp.addMouseListener(new java.awt.event.MouseAdapter() {

                    public void mouseClicked(java.awt.event.MouseEvent evt) {

                        evt.setSource(s);

                        panelPostMouseClickedPrev(evt);
                    }
                });

                scrollAllPosts.revalidate();
            }
        }

        labelMainTitle.setFont(new java.awt.Font("Chancery URW", 1, 48)); // NOI18N
        labelMainTitle.setForeground(new java.awt.Color(51, 151, 251));
        labelMainTitle.setText("Welcome to \"Daily Poster\"");

        scrollAllPosts.setViewportView(panelAllPosts);

        scrollAllPosts.setForeground(new Color(200, 250, 200));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel);
        jPanel.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(scrollAllPosts, javax.swing.GroupLayout.PREFERRED_SIZE, 711, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(89, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(scrollAllPosts, javax.swing.GroupLayout.DEFAULT_SIZE, 808, Short.MAX_VALUE)
                        .addGap(211, 211, 211))
        );

        btnBackToMainFromPrev.setText("Back to Main");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnBackToMainFromPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnBackToMainFromPrev, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
        );

        btnBackToMainFromPrev.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
                btnBackToMainFromPrev.setVisible(false);

                backToTheMainGUI(jPanel);
            }
        });

        pack();
    }

    private javax.swing.JButton btnAllowPost;
    private javax.swing.JButton btnBackToModer;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel labelPrevTitle;

    private void showSelectedFromModer(final JPanel jPanel, final String id) {
        jPanel.removeAll();
        labelPrevTitle = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        btnAllowPost = new javax.swing.JButton();
        btnBackToModer = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(800, 500));
        setMinimumSize(new java.awt.Dimension(800, 500));
        setPreferredSize(new java.awt.Dimension(850, 700));
        setResizable(false);

        labelPrevTitle.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        labelPrevTitle.setText(Client.prevPosts.get(Integer.parseInt(id)).getTitle());

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setText(Client.prevPosts.get(Integer.parseInt(id)).getDescriptionText());
        jScrollPane1.setViewportView(jTextArea1);

        btnAllowPost.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnAllowPost.setText("Allow");

        btnBackToModer.setBackground(new java.awt.Color(200, 250, 200));
        btnBackToModer.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnBackToModer.setText("Back");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel);
        jPanel.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(btnAllowPost, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnBackToModer, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(labelPrevTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 553, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 740, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(41, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnAllowPost, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnBackToModer, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(labelPrevTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 470, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
        );

        btnBackToModer.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
                toModer();
            }
        });

        btnAllowPost.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {

                Client.flushOutStream();

                Client.sendMessage("*a" + id + "|");

                showDialog("Post allowed!");
                
                //rewrite
                
                toModer();
            }
        });

        pack();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(800, 500));
        setMinimumSize(new java.awt.Dimension(800, 500));
        setPreferredSize(new java.awt.Dimension(850, 700));
        setResizable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 810, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1113, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void panelPostMouseClicked(java.awt.event.MouseEvent evt) {
        showSelectedPost(jPanel, (String) evt.getSource());
    }

    private void panelPostMouseClickedPrev(java.awt.event.MouseEvent evt) {
        showSelectedFromModer(jPanel, (String) evt.getSource());
    }

    private javax.swing.JButton btnAddNewPost;
    private javax.swing.JLabel labelMainTitle;
    private javax.swing.JPanel panelAllPosts;
    private javax.swing.JScrollPane scrollAllPosts;
    private javax.swing.JTextField textModer;

    public static boolean bAlreadySendM;

    private void backToTheMainGUI(final JPanel jPanel) {

        if (!bAlreadySendM) {
            Client.sendMessage("m|");
        } else {
            bAlreadySendM = false;
        }

        String message = Client.getMessage();

        try {
            fileStream.readString(message);
        } catch (IOException e) {
        }

        jPanel.removeAll();

        jPanel.setBackground(new Color(230, 230, 255));

        labelMainTitle = new javax.swing.JLabel();
        panelAllPosts = new javax.swing.JPanel();
        btnAddNewPost = new javax.swing.JButton();
        scrollAllPosts = new javax.swing.JScrollPane(panelAllPosts);
        textModer = new javax.swing.JTextField();

        final JScrollPane scrollPane = new JScrollPane(panelAllPosts);
        panelAllPosts.setLayout(new BoxLayout(panelAllPosts, BoxLayout.Y_AXIS));

        scrollAllPosts.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        JPanel jp;
        JLabel id;
        JLabel labelTitle;
        JTextArea labelDescription;

        for (int i = 0; i < Client.alPosts.size(); ++i) {

            if (Client.alPosts.get(i).isToPublish()) {

                jp = new JPanel();
                jp.setLayout(new GridBagLayout());
                jp.setPreferredSize(new Dimension(600, 200));
                jp.setMaximumSize(new Dimension(600, 200));
                jp.setBackground(new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255)));
                jp.setAlignmentX(JPanel.CENTER_ALIGNMENT);

                id = new JLabel();
                id.setName("PostID");
                id.setText(Integer.toString(i));
                id.setVisible(false);

                labelTitle = new JLabel();
                labelTitle.setFont(titleFont);
                labelTitle.setText(Client.alPosts.get(i).getTitle());

                labelDescription = new JTextArea();
                labelDescription.setEditable(false);
                labelDescription.setBackground(jp.getBackground());
                labelDescription.setText(Client.alPosts.get(i).getShortDescriptionText());
                labelDescription.setFont(descriptionFont);

                GridBagConstraints gbc = new GridBagConstraints();

                jp.add(id);

                gbc.gridx = 0;
                gbc.gridy = 0;
                //gbc.gridwidth = 2;

                jp.add(labelTitle, gbc);

                gbc.gridx = 0;
                gbc.gridy = 1;
                //gbc.gridwidth = 2;

                jp.add(labelDescription, gbc);
                alPostsPanels.add(jp);
                panelAllPosts.add(jp);
                final String s = id.getText();
                jp.addMouseListener(new java.awt.event.MouseAdapter() {

                    public void mouseClicked(java.awt.event.MouseEvent evt) {

                        Client.flushOutStream();

                        Client.sendMessage("s" + Short.parseShort(s) + "|");

                        evt.setSource(s);

                        panelPostMouseClicked(evt);
                    }
                });

                scrollAllPosts.revalidate();
            }
        }

        labelMainTitle.setFont(new java.awt.Font("Chancery URW", 1, 48)); // NOI18N
        labelMainTitle.setForeground(new java.awt.Color(51, 151, 251));
        labelMainTitle.setText("Welcome to \"Daily Poster\"");

        scrollAllPosts.setViewportView(panelAllPosts);

        scrollAllPosts.setForeground(new Color(200, 250, 200));

        btnAddNewPost.setText("Add New Post");

        btnAddNewPost.setBackground(new Color(200, 250, 200));

        textModer.setSize(100, 20);

        textModer.setVisible(true);

        textModer.setBackground(new Color(220, 250, 220));

        javax.swing.GroupLayout jPanelLayout = new javax.swing.GroupLayout(jPanel);
        jPanel.setLayout(jPanelLayout);
        jPanelLayout.setHorizontalGroup(
                jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelLayout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(scrollAllPosts, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(labelMainTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 28, 28)
                        .addComponent(btnAddNewPost)
                        .addContainerGap(459, Short.MAX_VALUE))
        );
        jPanelLayout.setVerticalGroup(
                jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(labelMainTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(scrollAllPosts, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnAddNewPost, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(260, 260, 260))
        );

        jPanel.add(textModer);

        pack();

        btnAddNewPost.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
                createNewPost(jPanel);
            }
        });

        textModer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfIDActionPerformed(evt);
            }
        });
    }

    private void tfIDActionPerformed(java.awt.event.ActionEvent evt) {

        String password = textModer.getText();

        if (password.equals(PASSWORD)) {
            toModer();
        } else {
            showDialog("Wrong Password!");
        }

    }

    public static void addPost(String sTitle, String sDescriptionText, String shortDesc,
            boolean isVIP, boolean bToPublish, short nPostID) {

        alPosts.add(new cPostInformation(sTitle, sDescriptionText, shortDesc,
                isVIP, bToPublish, nPostID));

//        try {
//            fileStream.write();
//        } catch (IOException e) {
//        }
    }

    private javax.swing.JButton btnAddPost;
    private javax.swing.JLabel labelTitleNewPost;
    private javax.swing.JLabel labelEnterTitle;
    private javax.swing.JLabel labelAddDescription;
    private javax.swing.JLabel labelModerCheck;
    private javax.swing.JTextArea textEnterTitle;
    private javax.swing.JTextArea textEnterDescription;

    private void createNewPost(final JPanel jPanel) {

        jPanel.removeAll();

        textEnterTitle = new javax.swing.JTextArea();
        labelTitleNewPost = new javax.swing.JLabel();
        textEnterDescription = new javax.swing.JTextArea();
        labelAddDescription = new javax.swing.JLabel();
        btnAddPost = new javax.swing.JButton();
        labelModerCheck = new javax.swing.JLabel();
        labelEnterTitle = new javax.swing.JLabel();

        labelTitleNewPost.setFont(new java.awt.Font("Chancery URW", 1, 48)); // NOI18N
        labelTitleNewPost.setForeground(new java.awt.Color(51, 151, 251));
        labelTitleNewPost.setText("Add New Post");

        labelAddDescription.setText("Description");

        btnAddPost.setText("Add Post");

        labelModerCheck.setText("Post will be posted after moderator check");

        labelEnterTitle.setText("Title");

        javax.swing.GroupLayout jPanelLayout = new javax.swing.GroupLayout(jPanel);
        jPanel.setLayout(jPanelLayout);
        jPanelLayout.setHorizontalGroup(
                jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 864, Short.MAX_VALUE)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelLayout.createSequentialGroup()
                                .addGap(89, 89, 89)
                                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanelLayout.createSequentialGroup()
                                                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(labelAddDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(btnAddPost)
                                                                .addComponent(labelModerCheck))
                                                        .addGap(388, 388, 388))
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLayout.createSequentialGroup()
                                                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                .addComponent(textEnterDescription)
                                                                .addComponent(textEnterTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 479, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGap(28, 28, 28)
                                                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(labelEnterTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGap(65, 65, 65)))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLayout.createSequentialGroup()
                                                .addGap(199, 199, 199)
                                                .addComponent(labelTitleNewPost, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(89, Short.MAX_VALUE)))
        );
        jPanelLayout.setVerticalGroup(
                jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(labelTitleNewPost, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41)
                                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(textEnterTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(labelEnterTitle))
                                .addGap(18, 18, 18)
                                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(32, 32, 32)
                                .addComponent(labelAddDescription)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(textEnterDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(46, 46, 46)
                                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE))
                                .addGap(18, 18, 18)
                                .addComponent(btnAddPost)
                                .addGap(42, 42, 42)
                                .addComponent(labelModerCheck)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(704, 704, 704))
        );

        pack();

        btnAddPost.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {

                String shortDesc = "";

                String message = "";

//                if (textEnterTitle.getText().length() < 10
//                        || textEnterTitle.getText().length() > 20) {
//                    showDialog("Title must be at least 10 symbols"
//                            + " and less then 20 symbols");
//                } else if (textEnterDescription.getText().length() < 50) {
//                    showDialog("Description must be at least 50 symbols");
//                } else {
                //++Client.nPostsCount;
                //50 => 1
                shortDesc = textEnterDescription.getText().substring(0, 1) + "...";

                Client.flushOutStream();

                message = "n" + textEnterTitle.getText()
                        + "*" + textEnterDescription.getText()
                        + "*" + shortDesc
                        + "*" + "false"
                        + "*" + "false"
                        + "*" + (Client.alPosts.size() + 1) + '|';

                Client.sendMessage(message);

//                    message = Client.getMessage();
//                    
//                    try {
//                        fileStream.readString(message);
//                    } catch (IOException e) {
//                    }
                showDialog("Will be posted after moderator check");

                bAlreadySendM = true;

                backToTheMainGUI(jPanel);
            }
            //}
        });

    }

    public void showDialog(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    private javax.swing.JButton btnDeletePost;
    private javax.swing.JButton btnMakePostVIP;
    private javax.swing.JButton btnShowGraph;
    private javax.swing.JButton btnToMainPage;
    private javax.swing.JLabel labelCurrentPostTitle;
    private javax.swing.JScrollPane scrollCurrentPostDescription;
    private javax.swing.JScrollPane scrollNotInUse;
    private javax.swing.JTextArea textCurrentPostDescription;
    private JTextArea textPassword;

    private void showSelectedPost(final JPanel jPanel, final String id) {
        jPanel.removeAll();

        labelCurrentPostTitle = new javax.swing.JLabel();
        scrollCurrentPostDescription = new javax.swing.JScrollPane();
        textCurrentPostDescription = new javax.swing.JTextArea();
        scrollNotInUse = new javax.swing.JScrollPane();
        btnToMainPage = new javax.swing.JButton();
        btnDeletePost = new javax.swing.JButton();
        btnMakePostVIP = new javax.swing.JButton();
        btnShowGraph = new JButton();
        textPassword = new JTextArea();

        labelCurrentPostTitle.setFont(titleFont);
        labelCurrentPostTitle.setText(Client.alPosts.get(Integer.parseInt(id)).getTitle());

        scrollCurrentPostDescription.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        textCurrentPostDescription.setColumns(20);
        textCurrentPostDescription.setRows(5);
        textCurrentPostDescription.setEditable(false);
        textCurrentPostDescription.setFont(descriptionFont);
        textCurrentPostDescription.setBackground(new Color(230, 230, 255));
        textCurrentPostDescription.setText(Client.alPosts.get(Integer.parseInt(id)).getDescriptionText());
        scrollCurrentPostDescription.setViewportView(textCurrentPostDescription);

        scrollNotInUse.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollNotInUse.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        btnToMainPage.setText("Main Page");

        btnDeletePost.setText("Delete Post");

        btnMakePostVIP.setText("Make VIP");

        btnShowGraph.setText("Show Graph");

        javax.swing.GroupLayout jPanelLayout = new javax.swing.GroupLayout(jPanel);
        jPanel.setLayout(jPanelLayout);
        jPanelLayout.setHorizontalGroup(
                jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanelLayout.createSequentialGroup()
                                        .addComponent(labelCurrentPostTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(12, 12, 12))
                                .addGroup(jPanelLayout.createSequentialGroup()
                                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(scrollCurrentPostDescription, javax.swing.GroupLayout.DEFAULT_SIZE, 626, Short.MAX_VALUE)
                                                .addComponent(scrollNotInUse))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(btnDeletePost, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(btnToMainPage, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(btnMakePostVIP, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(btnShowGraph, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 162, Short.MAX_VALUE))))
        );
        jPanelLayout.setVerticalGroup(
                jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelLayout.createSequentialGroup()
                        .addGap(0, 5, Short.MAX_VALUE)
                        .addComponent(labelCurrentPostTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGap(13, 13, 13)
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(scrollCurrentPostDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanelLayout.createSequentialGroup()
                                        .addComponent(btnToMainPage, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(13, 13, 13)
                                        .addComponent(btnDeletePost, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnMakePostVIP, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(13, 13, 13)
                                        .addComponent(btnShowGraph, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(28, 28, 28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scrollNotInUse, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(111, 111, 111))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(24, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(260, 260, 260))
        );

        btnToMainPage.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
                backToTheMainGUI(jPanel);
            }
        });

        btnShowGraph.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {

                Client.flushOutStream();

                Client.sendMessage("g" + id + "|");

                String sArrayX = Client.getMessage();

                short arraySize = Short.parseShort(sArrayX.substring(0, 2));

                sArrayX = sArrayX.substring(2, sArrayX.length() - 1);

                StringTokenizer strT = new StringTokenizer(sArrayX, "*");

                int[] nArrayX = new int[arraySize];

                int counter = 0;

                while (strT.hasMoreTokens()) {
                    nArrayX[counter] = Integer.parseInt(strT.nextToken());

                    ++counter;
                }

                GraphCanvas gCanvas = new GraphCanvas();

                gCanvas.setXArray(nArrayX);
                gCanvas.setYArray(arraySize);

                canvasGraph = gCanvas;

                showPostsGraph(jPanel);
            }
        });

        btnDeletePost.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {

                Client.flushOutStream();

                Client.sendMessage("d" + id + "|");

                bAlreadySendM = true;

                backToTheMainGUI(jPanel);
            }
        });

        btnMakePostVIP.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {

                Client.flushOutStream();

                Client.sendMessage("v" + id + "|");

                bAlreadySendM = true;

                backToTheMainGUI(jPanel);

            }
        });

        pack();
    }

    private java.awt.Canvas canvasGraph;
    private javax.swing.JLabel labelStatistic;
    private javax.swing.JLabel labelTitleGraph;
    private JButton btnToMainPageGraph;

    private void showPostsGraph(JPanel jPanel) {
        jPanel.removeAll();

        labelTitleGraph = new javax.swing.JLabel();
        labelStatistic = new javax.swing.JLabel();
        btnToMainPageGraph = new javax.swing.JButton();

        labelTitleGraph.setFont(new java.awt.Font("Chancery URW", 1, 24)); // NOI18N
        labelTitleGraph.setText("POST TITLE");

        labelStatistic.setText("Statistics");

        btnToMainPageGraph.setText("To Main page");
        btnToMainPageGraph.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnToMainPageGraphActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelLayout = new javax.swing.GroupLayout(jPanel);
        jPanel.setLayout(jPanelLayout);
        jPanelLayout.setHorizontalGroup(
                jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelLayout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(labelStatistic, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLayout.createSequentialGroup()
                                        .addComponent(labelTitleGraph, javax.swing.GroupLayout.PREFERRED_SIZE, 612, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnToMainPageGraph, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(canvasGraph, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(389, Short.MAX_VALUE))
        );
        jPanelLayout.setVerticalGroup(
                jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelLayout.createSequentialGroup()
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanelLayout.createSequentialGroup()
                                        .addGap(30, 30, 30)
                                        .addComponent(labelTitleGraph, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanelLayout.createSequentialGroup()
                                        .addGap(100, 100, 100)
                                        .addComponent(btnToMainPageGraph, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(5, 5, 5)
                        .addComponent(labelStatistic)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(canvasGraph, javax.swing.GroupLayout.PREFERRED_SIZE, 447, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(260, 260, 260))
        );

        pack();

        revalidate();

        repaint();

    }

    private void btnToMainPageGraphActionPerformed(ActionEvent e) {
        backToTheMainGUI(jPanel);
    }

    private JPanel jPanel;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
