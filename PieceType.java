//An enum to represent the 7 different types of Tetrominos.
//An enum is used instead of an int in order to help avoid errors and keep the code organized.

import java.util.Random;


public enum PieceType
{
  NULL, I, T, O, L, J, S, Z;
  
  public static PieceType getRandom()
  {
    Random rand = new Random();
    int randNum = rand.nextInt(7);
    switch(randNum)
    {
      case 0: return I;
      case 1: return T;
      case 2: return O;
      case 3: return L;
      case 4: return J;
      case 5: return S;
      case 6: return Z;
      default: return NULL;
    }
  }
}

