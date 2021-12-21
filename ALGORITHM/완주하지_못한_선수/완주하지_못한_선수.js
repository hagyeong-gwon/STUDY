function solution(participant, completion) {

  const completionMap = {};
  completion.forEach(c => completionMap[c] = completionMap[c] ? ++completionMap[c] : 1);

  for (const p of participant) {
    if (completionMap[p] > 0) completionMap[p]--;
    else return p;
  } 
  
  return answer;
}

const participant = ["leo", "kiki", "eden"];
const completion = ["eden", "kiki"];

console.log(solution(participant, completion));