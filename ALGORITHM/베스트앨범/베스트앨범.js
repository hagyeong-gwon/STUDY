function solution(genres, plays) {
  const genreMap = {}, answer = [];
  genres.forEach((genre, i) => {
    if (!genreMap[genre]) {
      genreMap[genre] = {
        plays: [],
        total: 0
      };
    }
    genreMap[genre].plays.push({index: i, playCnt: plays[i]});
    genreMap[genre].total += plays[i];
  });
  const genreRank = Object.values(genreMap).sort((a, b) => b.total - a.total);
  
  genreRank.forEach(rank=> {
    rank.plays.sort((a, b) => b.playCnt - a.playCnt);
    answer.push(...rank.plays.slice(0, 2).map(r=> r.index));
  });
  return answer;
}

const genres = ["classic", "pop", "classic", "classic", "pop"];
const plays = [500, 600, 150, 800, 2500];
console.log(solution(genres, plays));
