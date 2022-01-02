function solution(clothes) {
  const clotheMap = {};
  let answer = 1;
  clothes.forEach(c => clotheMap[c[c.length - 1]] = clotheMap[c[c.length - 1]] ? ++clotheMap[c[c.length - 1]] : 2);
  Object.values(clotheMap).forEach(c => answer *= c);
  
  return answer - 1;
}

const clothes = [["yellowhat", "headgear"], ["bluesunglasses", "eyewear"], ["green_turban", "headgear"]];

console.log(solution(clothes));