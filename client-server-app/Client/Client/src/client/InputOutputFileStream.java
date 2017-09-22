package client;
 
// указываем, что будем использовать IO Api
import java.io.*;
import java.util.ArrayList;

public class InputOutputFileStream {
 
    // класс для чтения файла
    private InputStream inputstream;
 
    // класс для записи в файл
    private OutputStream outputStream;
 
    // путь к файлу который будем читать и записывать
    private String path;
 
    public InputOutputFileStream(String path) {
        this.path = path;
    }
 
    public void readString(String _path) throws IOException {
        // инициализируем поток на чтение
        
        Client.alPosts = new ArrayList<cPostInformation>();
        
        byte[] bytes = _path.getBytes("UTF-8");
        
        inputstream = new ByteArrayInputStream(bytes);
 
        cPostInformation cip = new cPostInformation();
        
        String res = "";
        
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
        while(data != -1) {
            // преобразуем полученный байт в символ
            content = (char) data;
            
            if(flagJustBegin){
                if(content == '*'){
                    flagEmptyFile = true;
                    break;
                }
            }
            
            flagJustBegin = false;
            
            
            // выводим посимвольно
            //System.out.print(content);
            
            if(content != '*')
                res += content;
            
            if(flagNextTimeCheck){
                
                if(content == 't'){
                    nType = 1;
                }
                else if(content == 'd'){
                    nType = 2;
                }
                else if(content == 's'){
                    nType = 3;
                }
                else if(content == 'v'){
                    nType = 4;
                }
                else if(content == 'i'){
                    nType = 5;
                }
                else if(content == 'n'){
                    nType = 6;
                }
                else if(content == 'e'){
                    nType = 7;
                }
                
                flagNextTimeCheck = false;
                
                res = res.substring(0, res.length() - 1);
                
            }
            
            switch(nType){
                case 0:
                    break;
                case 1:
                    
                    cip.setPostID(++Client.nPostsCount);

                    Client.alPosts.add(cip);
                    
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
                                        
                    cip.setPostID(++Client.nPostsCount);
                    
                    Client.alPosts.add(cip);
                    
                    break;
            }
            
            nType = 0;
            
            if(content == '*')
                flagNextTimeCheck = true;
            
            data = inputstream.read();
        }
        // закрываем поток
        inputstream.close();
    }
 
}