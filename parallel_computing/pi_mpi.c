#include <stdio.h>
#include <stdlib.h>

#include <mpi.h>
#define N 100
double do_work(int s, int e);

int main(int argc, char** argv)
{
	double sum, local_sum;
	int world_size, world_rank, S, i;
	double comp_s, comm_s, comp_e, comm_e, comp_time, comm_time;
	MPI_Status status;
	/* Read A & B matrices from files
	 */
	MPI_Init(&argc, &argv);
	MPI_Comm_rank(MPI_COMM_WORLD, &world_rank);
	MPI_Comm_size(MPI_COMM_WORLD, &world_size);

	if(world_rank == 0 ) {
		scanf("%d",&S);
		printf("%d\n",S);
		while(S > 0) {
			MPI_Sendrecv(&local_sum, 1, MPI_DOUBLE, &S, 1, MPI_INT, MPI_ANY_SOURCE, 0, MPI_COMM_WORLD, &status);
			sum += local_sum;
		}
		for(i=0;i<world_size;i++) {
			int space[] = {0, 0};
			MPI_Send(space, 2, MPI_INT, i, 0, MPI_COMM_WORLD);
		}
		MPI_Reduce(&local_sum, &sum, 1, MPI_DOUBLE, MPI_SUM, 0, MPI_COMM_WORLD);
		printf("Pi calculated in parallel: %f\n", sum);
	} else {
		local_sum = 0;
		while(1) {
			MPI_Sendrecv(&local_sum, 1, MPI_DOUBLE ,0,0,S,1,MPI_INT,0,0, MPI_COMM_WORLD, &status);
			if(space[0] == space[1]) break;
			local_sum+=do_work((S-1)*N, S*N);
		}
		MPI_Reduce(&local_sum, &sum, 1, MPI_DOUBLE, MPI_SUM, 0, MPI_COMM_WORLD);
	}
	MPI_Finalize();
	return (0);
}

double do_work(int s, int e) {
	int i, factor;
	double sum;
	for(i = s;i < e;i++) {
		factor = (i%2==0)?1.0:-1.0;
		sum += factor/(2*i+1);
	}
	return sum;
}
