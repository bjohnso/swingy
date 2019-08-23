package com.swingy.id;

import java.util.ArrayList;

public class MobileIDAssigner {

        private ArrayList<String> mobileIDs = new ArrayList<>();

        public MobileIDAssigner(){
        }

        public String addID(String id){
            mobileIDs.add(id);
            return id;
        }

        public void removeID(String id){
            mobileIDs.remove(id);
        }

        public ArrayList<String> getIDs(){
            return mobileIDs;
        }
}
