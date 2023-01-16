const queue = [];
const numbers = [1, 1, 1, 1, 1];
const target = 3;

function solution(numbers, target) {
  let answer = 0;
  function find(index, sum) {
    if (numbers.length === index) {
      if (sum === target) ++answer;
      return;
    }

    find(index + 1, sum + numbers[index]);
    find(index + 1, sum - numbers[index]);
  }
  find(0, 0)

  return answer;
}

console.log(solution(numbers, target));
