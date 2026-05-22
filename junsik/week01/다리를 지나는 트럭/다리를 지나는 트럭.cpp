#include <string>
#include <vector>
#include <queue>

using namespace std;

int solution(int bridge_length, int weight, vector<int> truck_weights) {
    int answer = 0;
    int current_weight = 0; //다리위 무게
    int index = 0; //순번
    queue<int> bridge_truck; //다리위 트럭
    
    for(int i = 0; i < bridge_length; i++){
        bridge_truck.push(0);
    }
    
    while(index<truck_weights.size()){
        answer++; //시간 흐르게 하기 (절대값)
        
        current_weight -= bridge_truck.front();
        bridge_truck.pop();
        
        if(current_weight + truck_weights[index] <= weight){
            bridge_truck.push(truck_weights[index]);
            
            current_weight += truck_weights[index];
            
            index++;
        }
        else{
            bridge_truck.push(0);
        }
    }
    
    answer += bridge_length;
    
    return answer;
}