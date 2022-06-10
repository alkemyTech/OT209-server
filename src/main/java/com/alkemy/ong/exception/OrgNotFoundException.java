package com.alkemy.ong.exception;

public class OrgNotFoundException extends Exception{

        private final static String MODEL_DOESNT_EXIST = "id %s in model %s does not exist";
        public OrgNotFoundException(Long id, String modelName){
            super(String.format(MODEL_DOESNT_EXIST,id,modelName));
        }

        public OrgNotFoundException(){
            super();
        }
}
