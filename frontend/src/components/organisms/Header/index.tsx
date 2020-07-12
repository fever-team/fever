import React from 'react';
import { Text, Icon, Navbar, Button, Alignment } from '@blueprintjs/core';


export interface Props { }


const Header: React.FC<Props> = (props: Props) => {
  return (
    <Navbar>
      <Navbar.Group align={Alignment.LEFT}>
        <Navbar.Heading>
          <Text><Icon icon="flame" /> Fever</Text>
        </Navbar.Heading>
        <Navbar.Divider />
        <Button className="bp3-minimal" icon="home" text="Home" />
        <Button className="bp3-minimal" icon="lab-test" text="Test" />
      </Navbar.Group>
    </Navbar>
  )
}


export default Header;