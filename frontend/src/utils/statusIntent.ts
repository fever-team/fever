/*
 * Response의 status code로부터 intent(success, warning, danger)를 반환합니다.
 */

import { Intent } from '@blueprintjs/core';


const statusIntent = (status?: number | null): Intent => {
  if (!status) {
    return 'danger';
  }

  if (status <= 299) {
    return 'success';
  }

  if (status <= 399) {
    return 'warning';
  }

  return 'danger';
}


export default statusIntent;