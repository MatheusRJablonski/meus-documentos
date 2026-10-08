#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <unistd.h>
#define NUM_THREADS 2

struct Dados{
    int thread_id;
    int n;
};
void* contar(void* arg) {
    struct Dados *data = (struct Dados*)arg;
    int id = data->thread_id;
    int n = data->n;
    for (int i = 1; i <= n; i++) {
        printf("Thread %d: Contando %d\n", id, i);
        sleep(1);
    }
    return NULL;
}

void* contar2(void* arg) {
    struct Dados *data = (struct Dados*)arg;
    int id = data->thread_id;
    int n =data->n;    
    while(n--){
        printf("Thread %d: Contando %d\n", id, n+1);
        sleep(1);
    }
    return NULL;
}

int main() {
    pthread_t threads[NUM_THREADS];
    struct Dados thread_data[NUM_THREADS];
    int input = 0;
    
    printf("Digite o valor para a contagem: ");
    scanf("%d", &input);

    for(int i = 0; i < NUM_THREADS; i++) {
        thread_data[i].thread_id = i;
        thread_data[i].n = input;
    }
    pthread_create(&threads[0], NULL, contar, &thread_data[0]);
    pthread_create(&threads[1], NULL, contar2, &thread_data[1]);
    
    for(int i = 0; i < NUM_THREADS; i++) {
        pthread_join(threads[i], NULL);
    }

    printf("Contagem concluída!\n");
    return 0;
}