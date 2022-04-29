package edu.upc.dsa;

import edu.upc.dsa.models.Object;
import edu.upc.dsa.models.User;

import java.util.List;

public interface UserManager {


    public void addUser(String name, String surname, String birthday, String email, String password);
    public void addObject(String name, String description, double coins);
    public boolean login(String email,String password);
    public List<User> ListUsersAlf();
    public void buyObject(String email, String objectname);
    public List<Object> ListObjectsDescendingOrder();
    public List<Object> ObjectsCompradosPorUser(String email);





    /*public User addTrack(Track t);
    public Track getTrack(String id);
    public List<Track> findAll();
    public void deleteTrack(String id);
    public Track updateTrack(Track t);

    public int size();*/
}
