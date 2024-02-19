class MinHeap {
  constructor() {
    this.heap = [null]
  }
  heap_push(value) {
    this.heap.push(value);
    let currentIndex = this.heap.length - 1;
    let parentIndex = Math.floor(currentIndex / 2);

    while(parentIndex !== 0 && this.heap[parentIndex][1] > value[1]) {
      const temp = this.heap[parentIndex];
      this.heap[parentIndex] = this.heap[currentIndex];
      this.heap[currentIndex] = temp;
      currentIndex = parentIndex;
      parentIndex = Math.floor(currentIndex / 2);
    }
  }
  heap_pop() {
    if (this.heap.length === 2) return this.heap.pop();
    let returnValue = this.heap[1];
    this.heap[1] = this.heap.pop();
    let currentIndex = 1;
    let leftIndex = 2;
    let rightIndex = 3;
    while(true) {
      if (!this.heap[currentIndex]
      || (!this.heap[leftIndex] || this.heap[currentIndex][1] > this.heap[leftIndex][1])
      || (!this.heap[rightIndex] || this.heap[currentIndex][1] > this.heap[rightIndex][1])) {
        break;
      }


      const temp = this.heap[currentIndex];
      if (this.heap[leftIndex][1] > this.heap[rightIndex][1]) {
        this.heap[currentIndex] = this.heap[rightIndex];
        this.heap[rightIndex] = temp;
        currentIndex = rightIndex;
      } else {
        this.heap[currentIndex] = this.heap[leftIndex];
        this.heap[leftIndex] = temp;
        currentIndex = leftIndex;
      }
      leftIndex = currentIndex * 2
      rightIndex = leftIndex + 1
    }
    return returnValue;
  }
  heap_length() {
    return this.heap.filter(e => e).length;
  }
}

function solution(jobs) {
  let doingJob = null;
  const pendding = new MinHeap();

  jobs = jobs.sort((a,b) => {
    return a[0] - b[0]
  });


  let jobLength = jobs.length;
  const waitTimes = []
  let time = 0;

  while(jobs.length || pendding.heap_length()) {
    jobs.filter(job => job[0] <= time).forEach(job => {
      pendding.heap_push(job);
    })

    jobs = jobs.filter(job => job[0] > time);

    if (pendding.heap_length()) {
      doingJob = pendding.heap_pop();
      console.log(doingJob, pendding.heap)
      time += doingJob[1];
      console.log(time - doingJob[0])
      waitTimes.push(time - doingJob[0]);
    } else {
      time++;
    }
  }

  const sum =  waitTimes.reduce((pre, cur) => pre + cur, 0);

  return Math.floor(sum / jobLength)
}

console.log(solution(	[[1,1], [1,9], [0,3]]	))