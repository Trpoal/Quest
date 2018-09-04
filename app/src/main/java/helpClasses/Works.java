package helpClasses;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Works {
    final Random random = new Random();
    char mass[][];
    ArrayList<PossibleSt> PossArr = new ArrayList<>();
    ArrayList<PossibleSt> PossAI = new ArrayList<>();


    public void creat() {
        mass = new char[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                mass[i][j] = '0';
            }
        }
    }

    public boolean findPoss(int p) {
        boolean f = false;
        for (PossibleSt k : PossArr) {
            if (k.i == p) f = true;
        }
        return f;
    }

    public void deletePoss(int p, int o) {
        for (Iterator<PossibleSt> iterator = PossArr.iterator(); iterator.hasNext(); )
            if (iterator.next().i == p)
                iterator.remove();

    }

    public void changePoss(int i, int j) {
        if (PossArr.isEmpty()) {
            if ((i > 0) && (j > 0)) PossArr.add(new PossibleSt(i * 8 + j - 9));
            if (i > 0) PossArr.add(new PossibleSt(i * 8 + j - 8));
            if ((i > 0) && (j < 7)) PossArr.add(new PossibleSt(i * 8 + j - 8 + 1));
            if (j > 0) PossArr.add(new PossibleSt(i * 8 + j - 1));
            if (j < 7) PossArr.add(new PossibleSt(i * 8 + j + 1));
            if ((i < 7) && (j > 0)) PossArr.add(new PossibleSt(i * 8 + j + 7));
            if (i < 7) PossArr.add(new PossibleSt(i * 8 + j + 8));
            if ((i < 7) && (j < 7)) PossArr.add(new PossibleSt(i * 8 + j + 9));
        } else {
            deletePoss(i * 8 + j, j);
            if ((i > 0) && (j > 0)) if ((!findPoss(i * 8 + j - 9)) && (mass[i - 1][j - 1] == '0'))
                PossArr.add(new PossibleSt(i * 8 + j - 9));
            if (i > 0) if ((!findPoss(i * 8 + j - 8)) && (mass[i - 1][j] == '0'))
                PossArr.add(new PossibleSt(i * 8 + j - 8));
            if ((i > 0) && (j < 7))
                if ((!findPoss(i * 8 + j - 8 + 1)) && (mass[i - 1][j + 1] == '0'))
                    PossArr.add(new PossibleSt(i * 8 + j - 8 + 1));
            if (j > 0) if ((!findPoss(i * 8 + j - 1)) && (mass[i][j - 1] == '0'))
                PossArr.add(new PossibleSt(i * 8 + j - 1));
            if (j < 7) if ((!findPoss(i * 8 + j + 1)) && (mass[i][j + 1] == '0'))
                PossArr.add(new PossibleSt(i * 8 + j + 1));
            if ((i < 7) && (j > 0)) if ((!findPoss(i * 8 + j + 7)) && (mass[i + 1][j - 1] == '0'))
                PossArr.add(new PossibleSt(i * 8 + j + 7));
            if (i < 7) if ((!findPoss(i * 8 + j + 8)) && (mass[i + 1][j] == '0'))
                PossArr.add(new PossibleSt(i * 8 + j + 8));
            if ((i < 7) && (j < 7)) if ((!findPoss(i * 8 + j + 9)) && (mass[i + 1][j + 1] == '0'))
                PossArr.add(new PossibleSt(i * 8 + j + 9));
        }
    }

    public int stepMan(int h, boolean q) {
        int i = h / 8;
        int j = h % 8;
        int l = 0;
        boolean b;
        if (mass[i][j] == '0') {
            if (q) {
                mass[i][j] = '1';
                changePoss(i, j);
                b = FindWin(i, j, '1');
                if (b == true) l = 1;
            } else {
                mass[i][j] = '2';
                changePoss(i, j);
                b = FindWin(i, j, '2');
                if (b == true) l = 2;
            }
        }
        return l;
    }

    public boolean FindWin(int i, int j, char ch) {
        boolean b = false;
        int s = 0;
        for (int z = 0; z < 8; z++) {
            if (mass[z][j] == ch) {
                s++;
                if (s >= 4) {
                    b = true;
                    return b;
                }
            } else s = 0;
        }
        s = 0;
        for (int z = 0; z < 8; z++) {
            if (mass[i][z] == ch) {
                s++;
                if (s >= 4) {
                    b = true;
                    return b;
                }
            } else s = 0;

        }
        s = 0;
        if (i == j) {
            for (int z = 0; z < 8; z++) {
                if (mass[z][z] == ch) {
                    s++;
                    if (s >= 4) {
                        b = true;
                        return b;
                    }
                } else s = 0;
            }
        } else if (i > j) {
            s = 0;
            int r = 0;
            while ((i - j + r) < 8) {
                if (mass[i - j + r][r] == ch) {
                    s++;
                    if (s >= 4) {
                        b = true;
                        return b;
                    }
                } else s = 0;
                r++;
            }

        } else {
            s = 0;
            int r = 0;
            while ((j - i + r) < 8) {
                if (mass[r][j - i + r] == ch) {
                    s++;
                    if (s >= 4) {
                        b = true;
                        return b;
                    }
                } else s = 0;
                r++;
            }
        }
        if (i + j == 7) {
            s = 0;
            for (int r = 0; r < 8; r++) {
                if (mass[7 - r][r] == ch) {
                    s++;
                    if (s >= 4) {
                        b = true;
                        return b;
                    }
                } else s = 0;

            }
        } else if (i + j < 7) {
            int r = 0;
            s = 0;
            while (r <= (i + j)) {
                if (mass[i + j - r][r] == ch) {
                    s++;
                    if (s >= 4) {
                        b = true;
                        return b;
                    }
                } else s = 0;
                r++;
            }
        } else {
            int r = 0;
            s = 0;
            while ((i + j + r) < 15) {
                if (mass[7 - r][i + j - 7 + r] == ch) {
                    s++;
                    if (s >= 4) {
                        b = true;
                        return b;
                    }
                } else s = 0;
                r++;
            }
        }
        return b;
    }

    public boolean possibleG(int i, int j) {
        boolean b = false;
        int z = 0;
        while (((j + z) < 8) && (mass[i][j + z] != '1')) {
            z++;
        }
        int x = 1;
        while (((j - x) >= 0) && (mass[i][j - x] != '1')) {
            x++;
        }
        z += x - 1;
        if (z >= 4) b = true;
        return b;
    }

    public boolean possibleV(int i, int j) {
        boolean b = false;
        int z = 0;
        while (((i + z) < 8) && (mass[i + z][j] != '1')) {
            z++;
        }
        int x = 1;
        while (((i - x) >= 0) && (mass[i - x][j] != '1')) {
            x++;
        }
        z += (x - 1);
        if (z >= 4) b = true;
        return b;
    }

    public boolean possibleDG(int i, int j, char ch) {
        boolean b = false;
        int z = 0;
        while (((i + z) < 8) && ((j + z) < 8) && (mass[i + z][j + z] != ch)) {
            z++;
        }
        int x = 1;
        while (((i - x) >= 0) && ((j - x) >= 0) && (mass[i - x][j - x] != ch)) {
            x++;
        }
        z += (x - 1);
        if (z >= 4) b = true;
        return b;
    }

    public boolean possibleDP(int i, int j, char ch) {
        boolean b = false;
        int z = 0;
        while (((i + z) < 8) && ((j - z) >= 0) && (mass[i + z][j - z] != ch)) {
            z++;
        }
        int x = 1;
        while (((i - x) >= 0) && ((j + x) < 8) && (mass[i - x][j + x] != ch)) {
            x++;
        }
        z += (x - 1);
        if (z >= 4) b = true;
        return b;
    }

    public int PointFour(int n, char[] mass1) {
        int k = 0;
        int y = 0;
        for (int z = 0; z < 8; z++) {
            if ((n >= z) && (n - z < 4) && ((z + 3) < 7)) {
                if ((mass1[z] == 'x') && (mass1[z + 1] == 'x') && (mass1[z + 2] == 'x') && (mass1[z + 3] == 'x'))
                    y = 9999;
                else if ((mass1[z] == '0') && (mass1[z + 1] == 'x') && (mass1[z + 2] == 'x') && (mass1[z + 3] == 'x'))
                    y = 400;
                else if ((mass1[z] == 'x') && (mass1[z + 1] == 'x') && (mass1[z + 2] == 'x') && (mass1[z + 3] == '0'))
                    y = 400;
                else if ((mass1[z] == 'x') && (mass1[z + 1] == '0') && (mass1[z + 2] == 'x') && (mass1[z + 3] == 'x'))
                    y = 250;
                else if ((mass1[z] == 'x') && (mass1[z + 1] == 'x') && (mass1[z + 2] == '0') && (mass1[z + 3] == 'x'))
                    y = 250;
                else if ((mass1[z] == 'x') && (mass1[z + 1] == '0') && (mass1[z + 2] == '0') && (mass1[z + 3] == 'x'))
                    y = 60;
                else if ((mass1[z] == '0') && (mass1[z + 1] == 'x') && (mass1[z + 2] == 'x') && (mass1[z + 3] == '0'))
                    y = 85;
                else if ((mass1[z] == 'x') && (mass1[z + 1] == '0') && (mass1[z + 2] == 'x') && (mass1[z + 3] == '0'))
                    y = 75;
                else if ((mass1[z] == '0') && (mass1[z + 1] == 'x') && (mass1[z + 2] == '0') && (mass1[z + 3] == 'x'))
                    y = 75;
                k += y;
                y = 0;
            }
        }
        return k;
    }

    public int PointThree(int n, char[] mass1) {
        int k = 0;
        int y = 0;
        for (int z = 0; z < 8; z++) {
            if ((n >= z) && (n - z < 3) && (z + 2 < 7)) {
                if ((mass1[z] == 'x') && (mass1[z + 1] == '0') && (mass1[z + 2] == 'x')) y = 35;
                else if ((mass1[z] == '0') && (mass1[z + 1] == 'x') && (mass1[z + 2] == 'x'))
                    y = 50;
                else if ((mass1[z] == 'x') && (mass1[z + 1] == 'x') && (mass1[z + 2] == '0'))
                    y = 50;
                k += y;
                y = 0;
            }
        }
        return k;
    }

    public int AttackG(int i, int j, char ch) {
        int n = 0;
        char mass1[];
        int k = 0;
        mass1 = new char[7];
        mass[i][j] = 'm';
        if (j <= 3) {
            for (int x = 0; x < 7; x++) {
                if (mass[i][x] == 'm') {
                    mass1[x] = 'x';
                    n = x;
                } else if (mass[i][x] == ch) {
                    mass1[x] = 'x';
                } else {
                    mass1[x] = mass[i][x];
                }
            }
        } else {
            for (int x = 0; x < 7; x++) {
                if (mass[i][x + 1] == 'm') {
                    mass1[x] = 'x';
                    n = x;
                } else if (mass[i][x + 1] == ch) mass1[x] = 'x';
                else mass1[x] = mass[i][x + 1];
            }
        }
        k += PointFour(n, mass1);
        if (ch=='1'){
            double defPoint= k*0.9;
            k=(int)defPoint;
        }
        if (k == 0) {
            k += PointThree(n, mass1);
            if (ch=='1'){
                double defPoint= k*0.9;
                k=(int)defPoint;
            }
        }
        mass[i][j] = '0';
        return k;
    }

    public int AttackV(int i, int j, char ch) {

        char mass1[];
        int n = 0;
        int k = 0;
        mass[i][j] = 'm';
        mass1 = new char[7];
        if (i <= 3) {
            for (int x = 0; x < 7; x++) {
                if (mass[x][j] == 'm') {
                    mass1[x] = 'x';
                    n = x;
                } else if (mass[x][j] == ch) {
                    mass1[x] = 'x';
                } else {
                    mass1[x] = mass[x][j];
                }
            }
        } else {
            for (int x = 0; x < 7; x++) {
                if (mass[x + 1][j] == 'm') {
                    mass1[x] = 'x';
                    n = x;
                } else if (mass[x + 1][j] == ch) {
                    mass1[x] = 'x';
                } else mass1[x] = mass[x + 1][j];
            }
        }
        k += PointFour(n, mass1);
        if (ch=='1'){
            double defPoint= k*0.9;
            k=(int)defPoint;
        }
        if (k == 0) {
            k += PointThree(n, mass1);
            if (ch=='1'){
                double defPoint= k*0.9;
                k=(int)defPoint;
            }
        }
        mass[i][j] = '0';
        return k;
    }

    public int AttackDG(int i, int j, char ch) {
        char mass1[];
        int k = 0;
        int n = 0;
        mass[i][j] = 'm';
        mass1 = new char[7];
        if (i == j) {
            if (i <= 3) {
                for (int z = 0; z < 7; z++) {
                    if (mass[z][z] == 'm') {
                        mass1[z] = 'x';
                        n = z;
                    } else if (mass[z][z] == ch) mass1[z] = 'x';
                    else mass1[z] = mass[z][z];
                }
            } else {
                for (int z = 0; z < 7; z++) {
                    if (mass[z + 1][z + 1] == 'm') {
                        mass1[z] = 'x';
                        n = z;
                    } else if (mass[z + 1][z + 1] == ch) mass1[z] = 'x';
                    else mass1[z] = mass[z + 1][z + 1];
                }
            }
        } else if (i < j) {
            for (int z = 0; z < 7; z++) {
                if ((z + (j - i)) < 8) {
                    if (mass[z][z + j - i] == 'm') {
                        mass1[z] = 'x';
                        n = z;
                    } else if (mass[z][z + j - i] == ch) mass1[z] = 'x';
                    else mass1[z] = mass[z][z + j - i];
                } else mass1[z] = 'D';
            }
        } else {
            for (int z = 0; z < 7; z++) {
                if ((z + (i - j)) < 8) {
                    if (mass[z][z + i - j] == 'm') {
                        mass1[z] = 'x';
                        n = z;
                    } else if (mass[z][z + i - j] == ch) mass1[z] = 'x';
                    else mass1[z] = mass[z][z + i - j];
                } else mass1[z] = 'D';
            }
        }
        k += PointFour(n, mass1);
        if (ch=='1'){
            double defPoint= k*0.9;
            k=(int)defPoint;
        }
        if (k == 0) {
            k += PointThree(n, mass1);
            if (ch=='1'){
                double defPoint= k*0.9;
                k=(int)defPoint;
            }
        }
        mass[i][j] = '0';
        return k;
    }

    public int AttackDP(int i, int j, char ch) {
        char mass1[];
        int k = 0;
        int n = 0;
        mass[i][j] = 'm';
        mass1 = new char[7];
        if ((i + j) == 7) {
            if (i > j) {
                for (int z = 0; z < 7; z++) {
                    if (mass[7 - z][z] == 'm') {
                        mass1[z] = 'x';
                        n = z;
                    } else if ((mass[7 - z][z]) == ch) mass1[z] = 'x';
                    else mass1[z] = mass[7 - z][z];
                }
            } else {
                for (int z = 1; z < 8; z++) {
                    if (mass[7 - z][z] == 'm') {
                        mass1[z - 1] = 'x';
                        n = z - 1;
                    } else if ((mass[7 - z][z]) == ch) mass1[z - 1] = 'm';
                    else mass1[z - 1] = mass[7 - z][z];
                }
            }
        } else if ((i + j) < 7) {
            for (int z = 0; z < 7; z++) {
                if ((i + j - z) < 0) {
                    mass1[z] = 'D';
                } else {
                    if (mass[i + j - z][z] == 'm') {
                        mass1[z] = 'x';
                        n = z;
                    } else if ((mass[i + j - z][z]) == ch) mass1[z] = 'x';
                    else mass1[z] = mass[i + j - z][z];
                }
            }
        } else if ((i + j) > 7) {
            for (int z = 0; z < 7; z++) {
                if ((i + j + z) > 14) {
                    mass1[z] = 'D';
                } else {
                    if (mass[7 - z][i + j - 7 + z] == 'm') {
                        mass1[z] = 'x';
                        n = z;
                    } else if ((mass[7 - z][i + j - 7 + z]) == ch) mass1[z] = 'x';
                    else mass1[z] = mass[7 - z][i + j - 7 + z];
                }
            }
        }
        k += PointFour(n, mass1);
        if (ch=='1'){
            double defPoint= k*0.9;
            k=(int)defPoint;
        }
        if (k == 0) {
            k += PointThree(n, mass1);
            if (ch=='1'){
                double defPoint= k*0.9;
                k=(int)defPoint;
            }
        }
        mass[i][j] = '0';
        return k;
    }

    public int powerGorizontal(int i, int j) {
        int k = 0;
        if (possibleG(i, j)) k += AttackG(i, j, '2');
         if (k<9999) k += AttackG(i, j, '1');
        return k;
    }

    public int powerVertical(int i, int j) {
        int k = 0;
        if (possibleV(i, j)) k += AttackV(i, j, '2');
        if (k<9999) k += AttackV(i, j, '1');
        return k;
    }

    public int powerDiagonalG(int i, int j) {
        int k = 0;
        if (possibleDG(i, j, '1')) k += AttackDG(i, j, '2');
        if (k<9999) if (possibleDG(i, j, '2')) k += AttackDG(i, j, '1');
        return k;
    }

    public int powerDiagonalP(int i, int j) {
        int k = 0;
        if (possibleDP(i, j, '1')) k += AttackDP(i, j, '2');
        if (k<9999) if (possibleDP(i, j, '2')) k += AttackDP(i, j, '1');
        return k;
    }

    public int powerOfStep(int i, int j) {
        int k = 0;
        k += powerGorizontal(i, j);
        k += powerVertical(i, j);
        k += powerDiagonalG(i, j);
        k += powerDiagonalP(i, j);
        return k;
    }

    public int StepAi() {
        ArrayList<PossibleSt> PossAI = new ArrayList<>();

        int crd;
        int k;
        for (PossibleSt z : PossArr) {
            k = powerOfStep(z.i / 8, z.i % 8);
            if (k > 0) {
                PossAI.add(new PossibleSt(k, z.i));
            }
        }
        k = 0;
        for (PossibleSt m : PossAI) {
            if (k < m.i) k = m.i;
        }
        for (Iterator<PossibleSt> iterator = PossAI.iterator(); iterator.hasNext(); ) {
            if (iterator.next().i < (k * 0.95))
                iterator.remove();
        }
        int yy = random.nextInt(PossAI.size());
        crd = PossAI.get(yy).j;
        PossAI.clear();
        return crd;
    }
}