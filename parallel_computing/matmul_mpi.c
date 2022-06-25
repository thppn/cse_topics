#include <stdio.h>
#include <stdlib.h>

#include <mpi.h>

#define N 2048
#define Afile "Amat2048"
#define Bfile "Bmat2048"
#define Cfile "Cmat2048"

int A[N][N], B[N][N], C[N][N];
int readmat(char *fname, int *mat, int n), 
    writemat(char *fname, int *mat, int n);

int main(int argc, char** argv)
{
	int i, j, k, z, sum;
	int world_size, world_rank, local_n;
	int *local_C, *local_A;
	double comp_s, comm_s, comp_e, comm_e, comp_time, comm_time;
	/* Read A & B matrices from files
	 */
	MPI_Init(&argc, &argv);
	MPI_Comm_rank(MPI_COMM_WORLD, &world_rank);
	MPI_Comm_size(MPI_COMM_WORLD, &world_size);

	if(world_rank == 0 ) {
		if (readmat(Afile, (int *) A, N) < 0) 
			exit( 1 + printf("file problem\n") );
		if (readmat(Bfile, (int *) B, N) < 0) 
			exit( 1 + printf("file problem\n") );
	}

	local_n = N/world_size;
	local_C = (int*) malloc(sizeof(int) * N *local_n);
	local_A = (int*) malloc(sizeof(int) * N *local_n);

	comm_s = MPI_Wtime();
	//everyone should know B but A can be scattered
	MPI_Scatter(A, local_n*N, MPI_INT, local_A, local_n*N, MPI_INT, 0, MPI_COMM_WORLD);
	MPI_Bcast(B, N*N, MPI_INT, 0, MPI_COMM_WORLD);
	
	comp_s = MPI_Wtime();
	for (i = 0; i < local_n; i++)
		for (j = 0; j < N; j++)
		{
			for (k = sum = 0; k < N; k++)
				sum += local_A[i*N+k]*B[k][j];
			local_C[i*N+j] = sum;
		}
	comp_e = MPI_Wtime();
	MPI_Gather(local_C, local_n*N, MPI_INT, (int*) C, local_n*N, MPI_INT, 0, MPI_COMM_WORLD);
	comm_e = MPI_Wtime();

	free(local_C);
	free(local_A);

	if(world_rank == 0) {
		writemat(Cfile,(int*) C, N);

		comp_time = comp_e-comp_s;
		comm_time = comm_e-comm_s-comp_time;
		printf("Comp. time:%f, Comm. time:%f\n",comp_time,comm_time); 
	}
	MPI_Finalize();
	return (0);
}


/* Utilities to read & write matrices from/to files
 * VVD
 */

#define _mat(i,j) (mat[(i)*n + (j)])


int readmat(char *fname, int *mat, int n)
{
	FILE *fp;
	int  i, j;
	
	if ((fp = fopen(fname, "r")) == NULL)
		return (-1);
	for (i = 0; i < n; i++)
		for (j = 0; j < n; j++)
			if (fscanf(fp, "%d", &_mat(i,j)) == EOF)
			{
				fclose(fp);
				return (-1); 
			};
	fclose(fp);
	return (0);
}


int writemat(char *fname, int *mat, int n)
{
	FILE *fp;
	int  i, j;
	
	if ((fp = fopen(fname, "w")) == NULL)
		return (-1);
	for (i = 0; i < n; i++, fprintf(fp, "\n"))
		for (j = 0; j < n; j++) 
			fprintf(fp, " %d", _mat(i, j));
	fclose(fp);
	return (0);
}
