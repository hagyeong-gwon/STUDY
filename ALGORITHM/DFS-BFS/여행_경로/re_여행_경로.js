function solution(tickets) {
  const ticketMap = tickets.reduce((a, b) => {
    a[b[0]] = a[b[0]] || [];
    a[b[0]].push(b[1]);
    a[b[0]] = a[b[0]].sort();

    return a
  }, {});

  const result = find(ticketMap, 'ICN', tickets.length + 1, [])

  return result;
}

function find(ticketMap, now, cnt, root) {
  let nextTickets = ticketMap[now];

  if (!nextTickets || !nextTickets.length) {
    root.push(now)
    return root;
  }

  for (let i = 0; i< nextTickets.length; i++) {
    const next = nextTickets.shift();
    const map = {...ticketMap, [`${now}`]: nextTickets};

    root.push(now);

    const result = find(map, next, cnt, [...root]);
    if (result.length === cnt) {
      return result
    }
    root.pop()
    nextTickets.push(next);
    // console.log(nextTickets)
    // console.log(root, nextTickets, now, next)
  }

  return []
}

// [ 'ICN', 'ATL', 'ICN', 'SFO', 'ATL', 'SFO' ]
// console.log(solution([["ICN", "SFO"], ["ICN", "ATL"], ["ATL", "SFO"], ["ATL", "ICN"], ["ICN", "ICN"]]))
console.log(solution(
  [["ICN", "BOO"], ["ICN", "COO"], ["COO", "DOO"], ["DOO", "COO"], ["BOO", "DOO"], ["DOO", "BOO"], ["BOO", "ICN"], ["COO", "BOO"]]
))