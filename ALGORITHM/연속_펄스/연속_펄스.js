function solution(sequence) {
  let answer = 0;

  const calc = (arr) => {
    let sum = 0;
    for (let i = 0; i < arr.length; i++) {
      if (sum + arr[i] > 0) {
        sum = sum + arr[i];

        if (answer < sum) answer = sum;
      } else sum = 0;
    }
  }

  let x = 1;
  const plus = [];
  const minus = [];
  sequence.forEach(s => {
    plus.push(s * x);
    minus.push(s * x * -1);
    x = x * -1
  })


  calc(plus)
  calc(minus)

  return answer
}

console.log(solution(		[2, 3, -6, 1, 3, -1, 2, 4]))