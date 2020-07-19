import { Method } from 'axios';

import api from '../settings';


export interface TestOnceResponse {
  status: null | number;
  duration?: null | number;
}

const testOnce = async (url: string, method: Method): Promise<TestOnceResponse> => {
  try {
    const response = await api({
      url: url,
      method: method,
    });

    return {
      status: response.status,
      duration: response.config.metadata?.requestDuration,
    }
  } catch (error) {
    // Usually network error
    return {
      status: null,
      duration: null,
    }
  }
}


export default testOnce;