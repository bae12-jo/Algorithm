// 접미사 배열 구하기

public static class Suffix implements Comparable<Suffix>{
        int index;
        int rank, nextRank;

        public Suffix(int index, int rank){
            this.index = index;
            this.rank = rank;
        }

        public int compareTo(Suffix target){
            if(this.rank!=target.rank) return Integer.compare(this.rank, target.rank);
            return Integer.compare(this.nextRank, target.nextRank);
        }
    }

    public static int[] findSuffixArray(String str){
        int N = str.length();
        Suffix[] sa = new Suffix[N];

        for(int i=0; i<N; ++i){ // 각 접미사 첫 글자를 기준으로 사전순 rank 계산
            int rank = str.charAt(i)-'a';
            sa[i] = new Suffix(i, rank);
        }

        for(int i=0; i<N-1; ++i){ // 앞에서 두번째 글짜로 nextRank 계산
            sa[i].nextRank = sa[i+1].rank;
        }
        sa[N-1].nextRank = -1;

        Arrays.sort(sa); // rank와 nextRank로 오름차순 정렬

        int[] temp = new int[N];
        for(int length=4; length<2*N; length<<=1){ // logN 만큼 반복

            // rank 초기값 세팅
            int rank = 0, prev = sa[0].rank;
            sa[0].rank = rank;
            temp[sa[0].index] = 0;

            // 두번째 원소부터 rank값 갱신
            for(int i=1; i<N; ++i){
                // 앞선 접미사와 rank, nextRank 동일 여부 확인
                if(sa[i].rank == prev && sa[i].nextRank == sa[i-1].nextRank){
                    prev = sa[i].rank;
                    sa[i].rank = rank;
                }else{
                    prev = sa[i].rank;
                    sa[i].rank = ++rank;
                }
                temp[sa[i].index] = i;
            }

            // nextRank 갱신
            for(int i=0; i<N; ++i){
                int nextIdx = sa[i].index + (length/2);
                if(nextIdx >= N){
                    sa[i].nextRank = -1;
                    continue;
                }
                sa[i].nextRank = sa[temp[nextIdx]].rank;
            }

            Arrays.sort(sa);// 갱신된 rank, nextRank 기준으로 접미사 배열 재정렬
        }

        int[] suffixArray = new int[N];
        for(int i=0; i<N; ++i){
            suffixArray[i] = sa[i].index;
            // System.out.println(sa[i]);
        }

        return suffixArray;
    }
