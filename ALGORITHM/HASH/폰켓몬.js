function solution(nums) {
  const numMap = {};
  let length = 0;
  for (let i = 0; i < nums.length; i++) {
    if (!numMap[nums[i]]) {
      numMap[nums[i]] = true;
      if (++length === nums.length / 2) break;
    }
  }

  return length;
}

console.log(solution([3,1,2,3]))