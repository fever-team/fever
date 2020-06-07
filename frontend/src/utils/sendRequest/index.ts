import axios from 'axios';


export interface SendRequestResponse {
  status: number;
  time: number;
}

const sendRequest = async (url: string): Promise<SendRequestResponse> => {
  const startTime = Date.now();

  const { status } = await axios.get(url=url);

  // duration, (seconds)
  const duration = (Date.now() - startTime) / 1000;

  return {
    status: status,
    time: duration,
  }
}


export { sendRequest };