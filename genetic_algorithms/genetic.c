#include<stdio.h>
#include<math.h>
#include<stdlib.h>

#define MAX_POPULATION 10
#define GENERATIONS 100
#define SP 1.4
#define PARENTS 6
#define MUTATE_RATE 10

int chromosome[2][MAX_POPULATION];
double f_values[MAX_POPULATION];
double fitness[MAX_POPULATION];
double p_values[MAX_POPULATION];
double roulette_wheel[MAX_POPULATION];
int parent_index[PARENTS];

int i, population = 10;
double total_fitness;

// Utilities
int cmpfunc(const void * a, const void * b) {
   return ( *(double*)a - *(double*)b );
}
double randfrom(double min, double max) 
{
    double range = (max - min); 
    double div = RAND_MAX / range;
    return min + (rand() / div);
}
// Function to minimize
double f(double x1, double x2) {
    return (pow(x1,2)+x2);
}

// ΑΡΧΙΚΟΠΟΙΟΥΜΕ ΤΟ ΠΛΗΘΥΣΜΟ
void initialize_population() {
    int i;
    for(i=0;i < population;i++) {
        chromosome[0][i] = (rand() % 2048 ) - 1024;
        chromosome[1][i] = (rand() % 2048 ) - 1024;
    }
}
// ΓΙΑ ΚΑΘΕ ΧΡΩΜΟΣΩΜΑ ΥΠΟΛΟΓΙΖΟΥΜΕ ΤΗ ΤΙΜΗ ΤΗΣ ΣΥΝΑΡΤΗΣΗΣ
void get_fvalues() {
    int i;
    for(i=0;i < population;i++) {
        f_values[i] = f(chromosome[0][i],chromosome[0][i]);
    }
}
// ΓΙΑ ΚΑΘΕ ΧΡΩΜΟΣΩΜΑ ΥΠΟΛΟΓΙΖΟΥΜΕ ΤΟ ΒΑΘΜΟ ΙΚΑΝΟΤΗΤΑΣ (ΓΡΑΜΜΙΚΗ ΤΑΞΙΝΟΜΗΣΗ)
double evaluate_fitness() {
    qsort(f_values, population, sizeof(double), cmpfunc);
    total_fitness = 0;
    int i;
    for(i=0;i < population;i++) {
        fitness[i] = 2-SP+2*(SP-1) *(SP-1)/population;
        total_fitness += fitness[i];
    }
 }
// ΓΙΑ ΚΑΘΕ ΧΡΩΜΟΣΩΜΑ ΥΠΟΛΟΓΙΖΟΥΜΕ ΤΗ ΠΙΘΑΝΟΤΗΤΑ ΤΟΥ
void evaluate_prob() {
    int i;
    for(i=0;i < population;i++) {
        p_values[i] = fitness[i] / total_fitness;
    }
}
// ΑΦΟΥ ΥΠΟΛΟΓΙΣΤΕΙ Η ΠΙΘΑΝΟΤΗΤΑ ΕΠΙΛΕΓΟΥΜΕ ΤΟΥΣ ΝΕΟΥΣ ΓΟΝΕΙΣ ΜΕ ΤΗ ΜΕΘΟΔΟ ΤΗΣ ΡΟΥΛΕΤΑΣ
void select_roulette_wheel() {
    roulette_wheel[0] = p_values[0];
    int i, j, k;
    for(i=1;i<population;i++) {
        roulette_wheel[i] = roulette_wheel[i-1] + p_values[i];
    }
    qsort(roulette_wheel, population, sizeof(double), cmpfunc);
    double R;
    k = 0;
    for(i=0;i < PARENTS;i++) {
        R = randfrom(0, 1);
        j = 0;
        while(R < roulette_wheel[j]) j++;
        parent_index[k++] = j-1;
    }
}
// ΓΙΑ ΚΑΘΕ ΓΟΝΕΑ ΥΠΟΛΟΓΙΖΟΥΜΕ ΤΑ ΝΕΑ ΧΡΩΜΟΣΩΜΑΤΑ ΜΕ ΤΗ ΔΙΑΣΤΑΥΡΩΣΗ ΣΗΜΕΙΟΥ
void single_point_crossover() {
    int cp, i;
    int p1, p2, c1, c2, left_p1, right_p1, left_p2, right_p2;
    for(i=1;i<PARENTS;i+=2) {
        cp = rand() % 20;
        p1 = chromosome[0][parent_index[i-1]] << 10 + chromosome[1][parent_index[i-1]];
        p2 = chromosome[0][parent_index[i]] << 10 + chromosome[1][parent_index[1]];
        
        left_p1 = p1 >> cp;
        right_p1 = p1 << 20-cp;

        left_p2 = p2 >> cp;
        right_p2 = p2 << 20-cp;

        c1 = left_p1 + right_p2;
        c2 = left_p2 + right_p1;

        chromosome[0][population++] = c1;
        chromosome[1][population++] = c2;
    }

}
// ΜΕΤΑΛΛΑΣΟΥΜΕ ΤΑ ΧΡΩΜΟΣΩΜΑΤΑ ΓΙΑ ΠΙΘΑΝΟΤΗΤΑ MUTATE_RATE
void mutate_chromosomes() {
    int mp = rand() % 20;

    int mask = (int) pow(2,mp);

    int i;
    for(i=0;i<population;i+=MUTATE_RATE) {
        unsigned mutated_chromosome = chromosome[0][i] << 10 + chromosome[1][i];
        mutated_chromosome = mutated_chromosome ^ mask;
        chromosome[0][i] = mutated_chromosome >> 10;
        chromosome[1][i] = mutated_chromosome & 0b00000000001111111111;
    }

}
// ΕΝΗΜΕΡΩΝΟΥΜΕ ΤΟ ΠΛΗΘΥΣΜΟ ΜΕ ΤΑ ΝΕΑ ΧΡΩΜΟΣΩΜΑΤΑ
void update_population() {

}

int main(char** argc) {
    int g = 0;
    while(g++ < GENERATIONS) {
        initialize_population();
        get_fvalues();
        evaluate_fitness();
        evaluate_prob();
        select_roulette_wheel();
        single_point_crossover();
        mutate_chromosomes();
        update_population();
    }
}