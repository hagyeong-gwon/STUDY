function solution(phone_book) {
  for (let i = 1; i < 21; i++) {
    let charMap = {};
    let orgCharMap = {}
    for (let j = 0; j < phone_book.length; j++) {
      if (charMap[phone_book[j]] || orgCharMap[phone_book[j].slice(0, i)]) return false;
      charMap[phone_book[j].slice(0, i)] = true;
      orgCharMap[phone_book[j]] = true;
    }

    console.log(charMap)
  }
  return true;
}

console.log(solution(["119", "97674223", "1195524421"])	)