
import java.io.*;
import java.net.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.io.File;

public class Server {

    ////////////?!!!!!!!!!!!?////////////
    //MAKE SYNVHRONIZATION!!!!!!!!!//////
    ///////?////////!!!!!!//?////////////
    static final int PORT = 8080;
    public static ArrayList<cPostInformation> alPosts;
    public static ArrayList<cPostInformation> prevPosts;
    public static final String path = "E:\\Documents\\DevelopmentWorkspaces\\EclipseWorkspace\\Server\\src"
            + "\\data.txt";
    public static final String dayFile = "E:\\Documents\\DevelopmentWorkspaces\\EclipseWorkspace\\Server\\src"
            + "\\day.txt";

    public static final String prevPath = "E:\\Documents\\DevelopmentWorkspaces\\EclipseWorkspace\\Server\\src"
            + "\\prevData.txt";

    public static void main(String[] args) throws IOException {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        });

        alPosts = new ArrayList<cPostInformation>();
        prevPosts = new ArrayList<cPostInformation>();

        ServerSocket s = new ServerSocket(PORT);
        System.out.println("Server Started");
        try {
            while (true) {
                Socket socket = s.accept();

                System.out.println("Got a new connection");

                try {
                    new ServeOneJabber(socket);
                } catch (IOException e) {
                    socket.close();
                }
            }
        } finally {
            s.close();
        }
    }

    public static InputStream inputstream;

    public static OutputStream outputStream;

    public static String write() throws IOException {

        boolean bBegin = false;

        outputStream = new FileOutputStream(path);

        String sWrite = "";

        for (cPostInformation cip : alPosts) {

            if (bBegin) {
                sWrite += "*t";
            } else {
                bBegin = true;
            }

            sWrite += cip.getTitle();

            sWrite += "*d";

            sWrite += cip.getDescriptionText();

            sWrite += "*s";

            sWrite += cip.getShortDescriptionText();

            sWrite += "*v";

            sWrite += cip.isVIP();

            sWrite += "*i";

            sWrite += cip.isToPublish();

            sWrite += "*n";

            sWrite += cip.getPostID();

        }

        sWrite += "*e";

        outputStream.write(sWrite.getBytes());

        outputStream.close();

        return sWrite;
    }

    public static String writePrev() throws IOException {

        boolean bBegin = false;

        outputStream = new FileOutputStream(prevPath);

        String sWrite = "";

        for (cPostInformation cip : prevPosts) {

            if (bBegin) {
                sWrite += "*t";
            } else {
                bBegin = true;
            }

            sWrite += cip.getTitle();

            sWrite += "*d";

            sWrite += cip.getDescriptionText();

            sWrite += "*s";

            sWrite += cip.getShortDescriptionText();

            sWrite += "*v";

            sWrite += cip.isVIP();

            sWrite += "*i";

            sWrite += cip.isToPublish();

            sWrite += "*n";

            sWrite += cip.getPostID();

        }

        sWrite += "*e";

        outputStream.write(sWrite.getBytes());

        outputStream.close();

        return sWrite;
    }

    public static void writeDate(String date) throws IOException {

        outputStream = new FileOutputStream(dayFile);

        outputStream.write(date.getBytes());

        outputStream.close();
    }

    public static short readDate() throws IOException {
        // инициализируем поток на чтение

        String sRes = "";

        short nRes = 0;

        inputstream = new FileInputStream(dayFile);

        int data = inputstream.read();

        char content;

        // по байтово читаем весь файл
        while (data != -1) {
            // преобразуем полученный байт в символ
            content = (char) data;

            sRes += content;

            data = inputstream.read();
        }

        // закрываем поток
        inputstream.close();

        nRes = Short.parseShort(sRes);

        return nRes;
    }

    public static String read(String _path) throws IOException {
        // инициализируем поток на чтение

        alPosts.removeAll(alPosts);

        inputstream = new FileInputStream(path);

        cPostInformation cip = new cPostInformation();

        String res = "";

        String sReturn = "";

        boolean flagNextTimeCheck = false;

        boolean flagBegin = false;

        boolean flagJustBegin = true;

        boolean flagEmptyFile = false;

        short nType = 0;

        String shortDesc = "";

        // читаем первый символ в байтах (ASCII)
        int data = inputstream.read();
        char content;
        // по байтово читаем весь файл
        while (data != -1) {
            // преобразуем полученный байт в символ
            content = (char) data;

            sReturn += (char) data;

            if (flagJustBegin) {
                if (content == '*') {
                    flagEmptyFile = true;
                    break;
                }
            }

            flagJustBegin = false;

            // выводим посимвольно
            //System.out.print(content);
            if (content != '*') {
                res += content;
            }

            if (flagNextTimeCheck) {

                if (content == 't') {
                    nType = 1;
                } else if (content == 'd') {
                    nType = 2;
                } else if (content == 's') {
                    nType = 3;
                } else if (content == 'v') {
                    nType = 4;
                } else if (content == 'i') {
                    nType = 5;
                } else if (content == 'n') {
                    nType = 6;
                } else if (content == 'e') {
                    nType = 7;
                }

                flagNextTimeCheck = false;

                res = res.substring(0, res.length() - 1);

            }

            switch (nType) {
                case 0:
                    break;
                case 1:

                    cip.setPostID((short) (alPosts.size() + 1));

                    alPosts.add(cip);

                    cip = new cPostInformation();

                    res = "";

                    break;
                case 2:

                    cip.setTitle(res);

                    res = "";

                    break;
                case 3:

                    cip.setDescriptionText(res);

                    res = "";

                    break;
                case 4:

                    shortDesc = res.substring(0, 1);

                    shortDesc += "...";

                    cip.setShortDescriptionText(shortDesc);

                    res = "";

                    break;
                case 5:

                    cip.setVIP(Boolean.parseBoolean(res));

                    res = "";

                    break;
                case 6:
                    cip.setDaysToPublish(Boolean.parseBoolean(res));

                    res = "";
                    break;
                case 7:

                    cip.setPostID((short) (alPosts.size() + 1));

                    alPosts.add(cip);

                    break;
            }

            nType = 0;

            if (content == '*') {
                flagNextTimeCheck = true;
            }

            data = inputstream.read();
        }
        // закрываем поток
        inputstream.close();

        return sReturn;
    }

    public static void removePost(Short nPostID) {
        alPosts.remove(nPostID);
    }

    public static void makePostVIP(int id) {

        short nID = 0;

        File toRename;

        File file;

        if (id != 1) {
            alPosts.get(id - 1).setPostID((short) 1);

            for (int i = 0; i < id - 1; ++i) {

                nID = alPosts.get(i).getPostID();

                ++nID;

                alPosts.get(i).setPostID(nID);
            }

            Collections.sort(alPosts, new Comparator<cPostInformation>() {
                public int compare(cPostInformation o1, cPostInformation o2) {
                    if (o1.getPostID() < o2.getPostID()) {
                        return -1;
                    } else if (o1.getPostID() > o2.getPostID()) {
                        return 1;
                    } else {
                        return 0;
                    }
                }
            });

            file = new File("E:\\Documents\\DevelopmentWorkspaces\\"
                    + "EclipseWorkspace\\Server\\src\\GraphInfo\\"
                    + "graph" + id + ".txt");

            toRename = new File("E:\\Documents\\DevelopmentWorkspaces\\"
                    + "EclipseWorkspace\\Server\\src\\GraphInfo\\"
                    + "graph1.txt");

            file.renameTo(toRename);

            for (int i = id;
                    i < Server.alPosts.size() - id; ++i) {

                file = new File("E:\\Documents\\DevelopmentWorkspaces\\"
                        + "EclipseWorkspace\\Server\\src\\GraphInfo\\"
                        + "graph" + Integer.toString(i) + ".txt");

                toRename = new File("E:\\Documents\\DevelopmentWorkspaces\\"
                        + "EclipseWorkspace\\Server\\src\\GraphInfo\\"
                        + "graph" + Integer.toString(i - 1) + ".txt");

                file.renameTo(toRename);

            }

        }
    }
}

class ServeOneJabber extends Thread {

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public ServeOneJabber(Socket s) throws IOException {
        socket = s;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        // Включаем автоматическое выталкивание:
        out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket
                .getOutputStream())), true);
        // Если любой из вышеприведенных вызовов приведет к
        // возникновению исключения, то вызывающий отвечает за
        // закрытие сокета. В противном случае, нить
        // закроет его.
        start(); // вызываем run()
    }

    public void run() {

        String str = "";

        String sub = "";

        short nCurrentDaysCount = 0;

        short id;

        short nDay = 0;

        java.util.Date date;

        File newFile = null;

        File toRename;

        File file = null;

        cPostInformation cip = null;

        StringTokenizer strT = null;

        char c = ' ';

        try {
            while (true) {

//				do{
//					str += in.readLine();
//					
//					c = str.charAt(str.length());
//					
//				}while(c != '|');
                System.out.println("Reading line...");

                str = "";

                do {
                    c = (char) in.read();

                    str += c;
                } while (c != '|');

                if (str.equals("END")) {
                    break;
                }

                c = str.charAt(0);

                switch (c) {

                    case 'n':

                        System.out.println("In \"n\"");

                        str = str.substring(1, str.length() - 1);

                        cip = new cPostInformation();

                        strT = new StringTokenizer(str, "*");

                        cip.setTitle(strT.nextToken());
                        cip.setDescriptionText(strT.nextToken());
                        cip.setShortDescriptionText(strT.nextToken());
                        cip.setVIP(Boolean.parseBoolean(strT.nextToken()));
                        //cip.setDaysToPublish(Boolean.parseBoolean(strT.nextToken()));
                        strT.nextToken();
                        cip.setDaysToPublish(true);
                        cip.setPostID(Short.parseShort(strT.nextToken()));

                        Server.prevPosts.add(cip);

                        str = Server.writePrev();

                        str += '|';

                        out.write(str, 0, str.length());
                        //out.print("");
                        out.flush();
                        break;
                    case 'm':

                        System.out.println("In \"m\"");

                        str = Server.read(Server.path);

                        str += '|';

                        out.write(str, 0, str.length());
                        //out.print("");
                        out.flush();

                        break;

                    case 'r':

                        System.out.println("In \"n\"");

                        str = str.substring(1, str.length() - 1);

                        id = Short.parseShort(str);

                        Server.alPosts.add(Server.prevPosts.get(id));

                        Server.alPosts.get(id).setPostID((short) Server.alPosts.size());

                        Server.prevPosts.remove(id);

                        if (id != Server.prevPosts.size()) {
                              for(int i = id + 1; i < Server.alPosts.size(); ++i){
                                  Server.alPosts.get(i).setPostID(
                                  (short)(Server.alPosts.get(i).getPostID() + 1));
                              }
                        }

                        str = Server.writePrev();

                        str += '|';

                        out.write(str, 0, str.length());
                        //out.print("");
                        out.flush();

                        newFile = new File("E:\\Documents\\DevelopmentWorkspaces\\"
                                + "EclipseWorkspace\\Server\\src\\GraphInfo\\", "graph"
                                + Integer.toString(Server.alPosts.size()) + ".txt");

                        Server.outputStream = new FileOutputStream(newFile);

                        date = new java.util.Date();

                        if (date.getDate() < 10) {
                            Server.outputStream.write('0');
                        }

                        //01 - one day, 1 - one visit
                        Server.outputStream.write(new String(date.getDate() + "011" + "|").getBytes());

                        break;
                    case 'd':

                        System.out.println("In \"d\"");

                        str = str.substring(1, str.length() - 1);

                        id = Short.parseShort(str);

                        Server.alPosts.remove(id);

                        if (id < (Server.alPosts.size())) {
                            for (short i = id; i < Server.alPosts.size(); ++i) {
                                Server.alPosts.get(i).setPostID((short) (Server.alPosts.get(i).getPostID() - 1));
                            }
                        }

                        str = Server.write();

                        str += '|';

                        out.write(str, 0, str.length());
                        //out.print("");
                        out.flush();

                        file = new File("E:\\Documents\\DevelopmentWorkspaces\\"
                                + "EclipseWorkspace\\Server\\src\\GraphInfo\\"
                                + "graph" + Integer.toString(id + 1) + ".txt");

                        file.delete();

                        if (id < (Server.alPosts.size())) {
                            for (short i = id; i < Server.alPosts.size(); ++i) {

                                file = new File("E:\\Documents\\DevelopmentWorkspaces\\"
                                        + "EclipseWorkspace\\Server\\src\\GraphInfo\\"
                                        + "graph" + Integer.toString(i + 2) + ".txt");

                                toRename = new File("E:\\Documents\\DevelopmentWorkspaces\\"
                                        + "EclipseWorkspace\\Server\\src\\GraphInfo\\"
                                        + "graph" + Integer.toString(i + 1) + ".txt");

                                file.renameTo(toRename);

                            }
                        }
                        break;

                    case 'g':

                        System.out.println("In \"g\"");

                        str = str.substring(3, str.length() - 1);

                        short sht = Short.parseShort(str);

                        ++sht;

                        Server.inputstream = new FileInputStream(
                                "E:\\Documents\\DevelopmentWorkspaces\\"
                                + "EclipseWorkspace\\Server\\src\\GraphInfo\\"
                                + "graph" + Short.toString(sht) + ".txt");

                        short counter = 1;

                        do {
                            c = (char) Server.inputstream.read();

                            if (c == '*') {
                                ++counter;
                            }

                            str += c;

                        } while (c != '|');

                        str = Short.toString(counter) + "*" + str.substring(2, str.length()) + "|";

                        out.write(str, 0, str.length());
                        out.flush();

                        break;

                    case 's':

                        date = new java.util.Date();

                        short def;

                        str = str.substring(1, str.length() - 1);

                        id = Short.parseShort(str);

                        ++id;

                        str = "";

                        Server.inputstream = new FileInputStream(
                                "E:\\Documents\\DevelopmentWorkspaces\\"
                                + "EclipseWorkspace\\Server\\src\\GraphInfo\\"
                                + "graph" + id + ".txt");

                        do {
                            c = (char) Server.inputstream.read();

                            str += c;

                        } while (c != '|');

                        nCurrentDaysCount = Short.parseShort(str.substring(2, 4));

                        nDay = Short.parseShort(str.substring(0, 2));

                        str = str.substring(4, str.length() - 1);

                        //day passed
                        if (date.getDate() != nDay) {

                            strT = new StringTokenizer(str, "*");

                            //next month
                            if (date.getDate() < nDay) {

                                def = (short) ((nCurrentDaysCount - date.getDate())
                                        + (nCurrentDaysCount - nDay));

                                for (short i = def; i < nCurrentDaysCount - def; ++i) {
                                    strT.nextToken();
                                }

                                while (strT.hasMoreTokens()) {
                                    sub += strT.nextToken() + "*";
                                }

                                str = Short.toString(++nCurrentDaysCount);

                                for (int i = 0; i < date.getDate() - nDay - 1; ++i) {

                                    str += "0" + "*";

                                }
                            } //this month
                            else {

                                nCurrentDaysCount = (short) (nCurrentDaysCount + date.getDate() - nDay);

                                def = (short) (date.getDate() - nDay);

                                for (int i = date.getDate() - nDay; i < nCurrentDaysCount
                                        - date.getDate() - nDay; ++i) {
                                    strT.nextToken();
                                }

                                while (strT.hasMoreElements()) {
                                    sub += strT.nextToken() + "*";
                                }

                                str = Short.toString(++nCurrentDaysCount);

                                for (int i = 0; i < date.getDate() - nDay - 1; ++i) {

                                    str += "0" + "*";

                                }

                            }

                            str += "*1|";

                        } //this day
                        else {

                            if (nCurrentDaysCount == 30) {
                                str = str.substring(str.indexOf('*') + 1, str.length());

                                sub = str.substring(str.lastIndexOf('*') + 1, str.length());

                                def = Short.parseShort(sub);

                                ++def;

                                str = str.substring(0, str.lastIndexOf('*')) + Short.toString(def) + '|';
                            } else {

                                int current = 0;

                                if (nCurrentDaysCount != 1) {

                                    current = Integer.parseInt(str.substring(str.lastIndexOf('*'),
                                            str.length() - 1));
                                } else {
                                    current = Integer.parseInt(str.substring(0,
                                            str.length()));
                                }

                                ++current;

                                if (nCurrentDaysCount != 1) {
                                    str = str.substring(0, str.lastIndexOf('*')) + current + "|";
                                } else {
                                    str = current + "|";
                                }
                            }
                        }

                        if (date.getDate() < 10) {
                            str = "0" + date.getDate() + (nCurrentDaysCount < 10 ? "0" : "")
                                    + nCurrentDaysCount + str;
                        } else {
                            str = Integer.toString(date.getDate()) + nCurrentDaysCount + str;
                        }

                        Server.inputstream.close();

                        Server.outputStream = new FileOutputStream(
                                "E:\\Documents\\DevelopmentWorkspaces\\"
                                + "EclipseWorkspace\\Server\\src\\GraphInfo\\"
                                + "graph" + id + ".txt");

                        Server.outputStream.write(str.getBytes());

                        ++nDay;

                        Server.outputStream.close();

                        Server.writeDate(Short.toString(nDay));

                        break;

                    case 'v':

                        System.out.println("In \"v\"");

                        str = str.substring(1, str.length() - 1);

                        id = Short.parseShort(str);

                        ++id;

                        Server.makePostVIP(id);

                        str = Server.write();

                        str += '|';

                        out.write(str, 0, str.length());
                        //out.print("");
                        out.flush();

                        break;
                }

                //System.out.println("Echoing: " + str);
                //out.println(str);
            }
            System.out.println("closing...");
        } catch (IOException e) {
            System.err.println("IO Exception");
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println("Socket not closed");
            }
        }
    }
}
