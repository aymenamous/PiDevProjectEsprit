<?php

namespace CrowdRiseBundle;

use Symfony\Component\HttpKernel\Bundle\Bundle;

class CrowdRiseBundle extends Bundle
{
    
    public function getParent() {
        return 'FOSUserBundle';

    }

}
