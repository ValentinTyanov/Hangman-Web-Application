import useSWR from "swr";
import axios from "axios";

const fetcher = (url) => axios.get(url).then((res) => res.data);

function useTopTenEver() {
  const { data, error } = useSWR("/react-api/topten-ever", fetcher);
  return {
    topTenEver: data,
    isError: error,
  };
}

export default useTopTenEver;
