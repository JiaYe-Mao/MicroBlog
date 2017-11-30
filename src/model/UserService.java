package model;

import java.io.*;
import java.util.*;

public class UserService {

    private String USERS;

    public UserService (String USERS){
        this.USERS = USERS;
    }

    public boolean isUsernameExisted (String username){
        for (String file : new File(USERS).list()){
            if (file.equals(username)){
                return true;
            }
        }
        return false;
    }

    public void createUserData (String email, String username, String password) throws IOException {
        File userhome = new File(USERS + "/" + username);
        userhome.mkdir();
        BufferedWriter writer = new BufferedWriter(new FileWriter(userhome + "/profile"));
        writer.write(email + "\t" + password);
        writer.close();
    }

    public boolean checkLogin(String username, String password) throws IOException {
        if (username != null && password != null){
            for (String file : new File(USERS).list()){
                if (file.equals(username)){
                    BufferedReader reader = new BufferedReader(new FileReader(USERS + "/"
                            + file + "/profile"));
                    String passwd = reader.readLine().split("\t")[1];
                    if (passwd.equals(password)){
                        return true;
                    }
                }
                return false;
            }
        }
        return false;
    }

    private class TxtFilenameFilter implements FilenameFilter {
        @Override
        public boolean accept(File dir, String name) {
            return name.endsWith(".txt");
        }
    }

    private class DateComparator implements Comparator<Message> {
        @Override
        public int compare(Message o1, Message o2) {
            return -o1.getDate().compareTo(o2.getDate());
        }
    }

    public List<Message> getMessage(Message message) throws IOException {    //可以改成只要username这一个参数
        String username = message.getUsername();
        File border = new File(USERS + "/" + username);
        String[] txts = border.list(new TxtFilenameFilter());          //获得目录下所有txt文件

        List<Message> blahs = new ArrayList<>();

        for(String txt : txts) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                            new FileInputStream(USERS + "/" + username + "/" + txt), "UTF-8"));
            String text = null;
            StringBuilder builder = new StringBuilder();
            while((text = reader.readLine()) != null) {
                builder.append(text);
            }
            Date date = new Date(Long.parseLong(txt.substring(0, txt.indexOf(".txt"))));
            reader.close();
            blahs.add(new Message(username, date, builder.toString()));
        }
        blahs.sort(new DateComparator());
        return blahs;
    }

    public void addMessage(Message message) throws IOException {
        String file = USERS + "/" + message.getUsername() + "/" + new Date().getTime() + ".txt";
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));   //utf-8
        writer.write(message.getTxt());
        writer.close();
    }

    public void deleteMessage(Message message){
        File file = new File(USERS+"/"+ message.getUsername()+"/"+ message.getDate().getTime() +".txt");
        if (file.exists()){
            file.delete();
        }
    }



}
