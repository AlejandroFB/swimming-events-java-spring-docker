package com.zwemmen.psv.generic;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Base class configuration for use with integration tests.
 *
 * @author afernandez
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public abstract class BaseIntegrationTest {

    @Before
    public abstract void init() throws Exception;

    @After
    public abstract void cleanUp() throws Exception;
}