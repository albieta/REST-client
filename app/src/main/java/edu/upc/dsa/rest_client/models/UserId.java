package edu.upc.dsa.rest_client.models;

public class UserId {
    String idUser;

    public UserId(){};

    public UserId(String id) {
        this.setIdUser(id);
    }

    public String getIdUser() {
        return idUser;
    }
    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }
}