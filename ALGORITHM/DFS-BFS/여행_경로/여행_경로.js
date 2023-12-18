function solution(tickets) {
  let answer = [];

  const map = tickets.reduce((a, b) => {
    a[b[0]] = a[b[0]] || [];
    a[b[0]].push(b[1]);
    return a;
  }, {});

  // Object.values(map).forEach(e => e = e.sort());


  answer = find(map, answer, 'ICN', tickets.length + 1)

  return answer;
}

function find(map, answer, now, correctCnt) {
  answer.push(now);

  if (!map[now] || !map[now].length) {
    return answer;
  }
  map[now] = map[now].sort();

  while (map[now].length) {
    const next = map[now].shift();
    const result = find(map, JSON.parse(JSON.stringify(answer)), next, correctCnt);
    if (result.length === correctCnt) return result;
    else map[now].push(next);
  }
}

console.log(solution([["ICN", "SFO"], ["ICN", "ZEO"], ["ZEO", "ICN"]]))