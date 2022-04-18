/**
@author Saina Ghasemian-Roudsari, Rachel Dalton, Ana Perrone, Isaiah Lemieux<a 
@version 1.8
@since   1.0
*/
package edu.ucalgary.ensf409;

//Enumerations class using toString method
public enum ClientTypes {
    ADULTFEMALE {
        public String toString() {
            return "Adult Female";
        }
    },

    ADULTMALE {
        public String toString() {
            return "Adult Male";
        }
    },

    CHILDOVER8 {
        public String toString() {
            return "Child over 8";
        }
    },

    CHILDUNDER8 {
        public String toString() {
            return "Child under 8";
        }
    };
    
    public abstract String toString();
}