function solution(n) {
  let numbers = [1, 1, 3, 10, 23, 62, 170, 441];

  for (let i = 8; i <= n; i++) {
    let sum = numbers[i-1] + numbers[i-2] * 2 + numbers[i-3] * 6 + numbers[i-4] - numbers[i - 6];

    numbers.push((sum+1000000007) % 1000000007);
  }
  return numbers[n] % 1000000007;
}

console.log(solution(8))