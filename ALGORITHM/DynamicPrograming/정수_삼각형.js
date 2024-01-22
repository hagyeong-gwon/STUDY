function solution(triangle) {
  let answer = 0;
  for (let i = 0; i< triangle.length - 1; i++) {
    const arr = [];

    for (let y = 0; y < triangle[i].length; y++) {
      const left = triangle[i][y] + triangle[i + 1][y]
      const right = triangle[i][y] + triangle[i + 1][y + 1]

      arr[y] = (arr[y] || 0) > left ? arr[y] : left
      arr[y + 1] = (arr[y + 1] || 0) > right ? arr[y + 1] : right

      if (answer < arr[y]) answer = arr[y];
      else if(answer < arr[y+1]) answer = arr[y+1];
    }

    triangle[i + 1] = arr;
  }

  return answer;
}
console.log(solution([[7], [3, 8], [8, 1, 0]]))