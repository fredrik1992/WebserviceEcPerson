package com.example.personsrest.remote;

import com.example.personsrest.KeyCloakToken;

public interface GroupRemote {

    String getNameById(String groupId);


    String createGroup(String name); // måste fråga om detta är ok

    String removeGroup(String name);


}

