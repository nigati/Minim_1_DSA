package edu.upc.dsa;

import edu.upc.dsa.models.User;
import edu.upc.dsa.models.Object;

import java.util.*;

import org.apache.log4j.Logger;

public class UserManagerImpl implements UserManager {
    private static UserManager instance;
    private int numusers;
    private int numobjects;
    private HashMap<String, User> hmUsers;
    protected List<User> users;
    protected List<Object> objects;
    final static Logger logger = Logger.getLogger(UserManagerImpl.class);

    private UserManagerImpl() {
        this.users = new LinkedList<>();
        this.objects = new LinkedList<>();
        hmUsers = new HashMap<String,User>();

    }

    public static UserManager getInstance() {
        if (instance==null) instance = new UserManagerImpl();
        return instance;
    }

    @Override
    public void addUser(String name, String surname, String birthday, String email, String password) {
        boolean exists = false;
        for(int i = 0; i<this.numusers; i++) {
            if (email.equals(this.users.get(i).getEmail())) {
                exists = true;
                break;
            }

        }
        if (exists) {
            logger.warn("User with email:"+email+" already exists");
        }
        else {
            User user = new User();
            user.setName(name);
            user.setSurname(surname);
            user.setBirthday(birthday);
            user.setEmail(email);
            user.setPassword(password);
            user.setBalance(50);
            this.users.add(user);
            logger.info ("The user with email"+email+"has been added");
            this.numusers+=1;
        }
    }
    @Override
    public void addObject(String name, String description, double coins) {
        Object object = new Object();
        object.setName(name);
        object.setDescription(description);
        object.setCoins(coins);
        this.objects.add(object);
        logger.info("Se ha añadido el objeto correctamente");
        this.numobjects+=1;
    }
    @Override
    public boolean login (String email, String password) {
        boolean auth = false;
        for (int i = 0; i < this.numusers; i++) {
            if (email.equals(this.users.get(i).getEmail()) && password.equals(this.users.get(i).getPassword())) {
                auth = true;
                break;

            }

        }
        if (auth){
            logger.info("The authentications has been completed successfully");
        }
        else{
            logger.warn("The user does not exist or the password is not correct");
        }
        return auth;
    }

    @Override
    public List<User> ListUsersAlf() {
        Collections.sort(users);
        logger.info("Hago sort del array");
        return users;
    }

    @Override
    public void buyObject(String email, String objectname) {
        Object object = new Object();
        for (int i = 0; i < this.numobjects; i++) {
            if (objectname.equals(this.objects.get(i).getName())) {
                object = this.objects.get(i);
                break;
            }
        }
        for (int i = 0; i < this.numusers; i++) {
            if (email.equals(this.users.get(i).getEmail())) {
                if (this.users.get((i)).getBalance() >= object.getCoins()){
                    double newbalance = this.users.get((i)).getBalance()-object.getCoins();
                    this.users.get((i)).addObject(object);
                    this.users.get((i)).setBalance(newbalance);
                    logger.info("Se ha realizado la compra, nuevo balance:"+newbalance);
                }
                else{
                    logger.warn("No se ha podido realizar la compra");
                }
                break;
            }
        }



    }

    @Override
    public List<Object> ListObjectsDescendingOrder() {
        Collections.sort(objects, (o1, o2) ->
                Double.compare(o1.getCoins(), o2.getCoins()));
        return objects;
    }
   @Override
    public List<Object> ObjectsCompradosPorUser(String email) {
        User user = new User();
        for (int i = 0; i < this.numusers; i++) {
            if (email.equals(this.users.get(i).getEmail())) {
                user=this.users.get(i);
                logger.info("Paso lista de objetos comprados por user:" + email);
                break;
            } else {
                logger.warn("No encuentro user con este email");
            }
        }
        return user.getListObjects();
    }
}