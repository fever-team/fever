import { atom } from 'recoil';

import { Method } from '../../components/molecules/MethodSelector';


const methodState = atom<Method>({
  key: 'requester-method',
  default: 'GET',
});


export default methodState;