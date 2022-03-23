function solution(progresses, speeds) {
  let answer = [];
	let progresses_ing = progresses.map((p, i)=> Math.ceil((100 - p) / speeds[i]));
  while (progresses_ing.length) {
		const first = progresses_ing[0];
		let result = 0;
	  for (const p of progresses_ing) {
			if (p - first <= 0) result++
			else break;
	  }
		answer.push(result)
	  progresses_ing = progresses_ing.slice(result)
  }
  return answer;
}

const progresses = [95, 90, 99, 99, 80, 99];
const speeds = [1, 1, 1, 1, 1, 1];

solution(progresses, speeds)