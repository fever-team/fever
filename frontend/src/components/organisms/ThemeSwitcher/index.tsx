import React from 'react';

import { Switch, Position, Tooltip } from '@blueprintjs/core';


export interface Props {}


const ThemeSwitcher: React.FC<Props> = (props: Props) => {
  return (
    <Tooltip content="Switch light/dark theme." position={Position.BOTTOM}>
      <Switch label="Switch Theme" />
    </Tooltip>
  )
}


export default ThemeSwitcher;