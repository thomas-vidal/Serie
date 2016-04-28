package com.thomsvdl.serie.Serialization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.StreamCorruptedException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thomas vidal on 28/04/2016.
 */
public class SerieSerialization<T> {

    public void Serialize(FileOutputStream fos,List<T> list){
        try
        {
            ObjectOutputStream out = new ObjectOutputStream(fos);
            out.writeObject(list);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<T> Deserialize(FileInputStream fis){
        ArrayList<T> list = null;
        try {
            ObjectInputStream in = new ObjectInputStream(fis);
            list = (ArrayList<T>) in.readObject();
            in.close();
        }catch (OptionalDataException e) {
            e.printStackTrace();
        } catch (StreamCorruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return list;
    }
}
