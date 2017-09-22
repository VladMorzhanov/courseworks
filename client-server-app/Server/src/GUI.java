
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

public class GUI extends javax.swing.JFrame {

    public GUI() {

        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rootPanel = new javax.swing.JPanel();
        lbID = new javax.swing.JLabel();
        tfID = new javax.swing.JTextField();
        lbTitle = new javax.swing.JLabel();
        graphCanvas = new GraphCanvas();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(800, 500));
        setMinimumSize(new java.awt.Dimension(800, 500));
        setPreferredSize(new java.awt.Dimension(800, 500));
        setResizable(false);

        lbID.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbID.setForeground(new java.awt.Color(102, 102, 255));
        lbID.setText("Enter ID of post to show graph");

        tfID.setText("ID");
        tfID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfIDActionPerformed(evt);
            }
        });

        lbTitle.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbTitle.setForeground(new java.awt.Color(204, 0, 204));
        lbTitle.setText("Post Title");

        javax.swing.GroupLayout rootPanelLayout = new javax.swing.GroupLayout(rootPanel);
        rootPanel.setLayout(rootPanelLayout);
        rootPanelLayout.setHorizontalGroup(
            rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rootPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(graphCanvas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(rootPanelLayout.createSequentialGroup()
                        .addGroup(rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lbTitle, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbID, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfID, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        rootPanelLayout.setVerticalGroup(
            rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rootPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbID, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfID, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(graphCanvas, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rootPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rootPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfIDActionPerformed
        //take ID and draw graph

        proceedInvalidate = true;

        try {
            Server.read(Server.path);
        } catch (IOException e) {
        }
        short id = Short.parseShort(tfID.getText());

        if (Server.alPosts.size() < id || id == 0) {
            showDialog("Error! No posts found!");
        } else {

            String str = "";

            char c = '0';

            try {
                Server.inputstream = new FileInputStream(
                        "E:\\Documents\\DevelopmentWorkspaces\\"
                        + "EclipseWorkspace\\Server\\src\\GraphInfo\\"
                        + "graph" + Short.toString(id) + ".txt");
            } catch (FileNotFoundException e) {
            }

            short counter = 1;

            try {
                do {
                    c = (char) Server.inputstream.read();

                    if (c == '*') {
                        ++counter;
                    }

                    str += c;

                } while (c != '|');

            } catch (IOException e) {
            }

            short arraySize = Short.parseShort(str.substring(0, 2));

            str = str.substring(2, str.length() - 1);

            StringTokenizer strT = new StringTokenizer(str, "*");

            int[] nArrayX = new int[arraySize];

            int count = 0;

            while (strT.hasMoreTokens()) {
                nArrayX[count] = Integer.parseInt(strT.nextToken());

                ++count;
            }

            graphCanvas.setXArray(nArrayX);
            graphCanvas.setYArray(arraySize);

            try {
                Server.inputstream.close();
            } catch (IOException e) {
            }

            try {
                Server.inputstream = new FileInputStream(
                        "E:\\Documents\\DevelopmentWorkspaces\\"
                        + "EclipseWorkspace\\Server\\src\\data.txt");
            } catch (FileNotFoundException e) {
            }

            str = "";

            if (id == 1) {
                try {
                    do {
                        c = (char) Server.inputstream.read();

                        if (c == '*') {
                            break;
                        }

                        str += c;

                    } while (c != '|' && c != '*');
                } catch (IOException e) {
                }
            } else {

                try {
                    do {
                        c = (char) Server.inputstream.read();

                        if (c == '|') {
                            break;
                        }

                        if (c == '*') {
                            c = (char) Server.inputstream.read();

                            if (c == 'n') {
                                do {
                                    c = (char) Server.inputstream.read();

                                    if (c == '|') {
                                        break;
                                    }

                                    if (c == '*') {
                                        break;
                                    }

                                    str += c;
                                } while (c != '*');

                                if (Short.parseShort(str) == id - 1) {
                                    break;
                                } else {
                                    str = "";
                                }
                            }
                        }
                    } while (c != '|');

                    c = (char) Server.inputstream.read();

                    str = "";

                    do {
                        c = (char) Server.inputstream.read();

                        if (c == '*') {
                            break;
                        }

                        str += c;
                    } while (c != '*' && c != '|');
                } catch (IOException e) {
                }
            }
            lbTitle.setText(str);

            try {
                Server.inputstream.close();
            } catch (IOException e) {
            }

            rootPanel.invalidate();
            rootPanel.revalidate();
            graphCanvas.invalidate();
            graphCanvas.repaint();
            graphCanvas.revalidate();
            rootPanel.repaint();
        }

    }//GEN-LAST:event_tfIDActionPerformed

    private void showDialog(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    static boolean proceedInvalidate = false;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private GraphCanvas graphCanvas;
    private javax.swing.JLabel lbID;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JPanel rootPanel;
    private javax.swing.JTextField tfID;
    // End of variables declaration//GEN-END:variables
}
