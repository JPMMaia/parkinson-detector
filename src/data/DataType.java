package data;

import com.sun.javaws.exceptions.InvalidArgumentException;

/**
 * Created by Miguel on 17-05-2015.
 */
public enum DataType
{
    VowelA, VowelO, VowelU,
    Number1, Number2, Number3, Number4, Number5, Number6, Number7, Number8, Number9, Number10,
    Phrase1, Phrase2, Phrase3, Phrase4,
    Word1, Word2, Word3, Word4, Word5, Word6, Word7, Word8, Word9;

    public static DataType intToType(int type)
    {
        switch(type)
        {
            case 0:
                return VowelA;
            case 1:
                return VowelO;
            case 2:
                return VowelU;
            case 3:
                return Number1;
            case 4:
                return Number2;
            case 5:
                return Number3;
            case 6:
                return Number4;
            case 7:
                return Number5;
            case 8:
                return Number6;
            case 9:
                return Number7;
            case 10:
                return Number8;
            case 11:
                return Number9;
            case 12:
                return Number10;
            case 13:
                return Phrase1;
            case 14:
                return Phrase2;
            case 15:
                return Phrase3;
            case 16:
                return Phrase4;
            case 17:
                return Word1;
            case 18:
                return Word2;
            case 19:
                return Word3;
            case 20:
                return Word4;
            case 21:
                return Word5;
            case 22:
                return Word6;
            case 23:
                return Word7;
            case 24:
                return Word8;
            case 25:
                return Word9;
            
            default:
                throw new IllegalArgumentException("Invalid index to enum: " + type);
        }
    }
}
