package edu.miamioh.cse.finalproject.shell;


import edu.miamioh.cse.finalproject.core.BarCoding;
import edu.miamioh.cse.finalproject.core.TranslateMessage;
import edu.miamioh.cse.finalproject.core.ZipCoding;
import org.junit.Before;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RouterTest {

    private BarCoding barCoding;
    private ZipCoding zipCoding;

    @Before
    public void setUp() throws Exception {
        barCoding = mock(BarCoding.class);
        zipCoding = mock(ZipCoding.class);
    }

    @Test
    public void should_print_normal_menu_when_start(){
        Router router =new Router(mock(Command.class), barCoding, zipCoding);
        String message = router.start();
        assertThat(message).isEqualTo("I can accept these commands:\n a. input zipcode, translate to barcode. \n b. input barcode, translate to zipcode. \n c. quit. \n What is your command? \n");
    }

    @Test
    public void should_exit_when_input_c(){
        Command exit = mock(ExitCommand.class);
        when(exit.fit(anyString())).thenCallRealMethod();
        Router router =new Router(exit, barCoding, zipCoding);
        
        String message = router.start();
        router.acceptCommand("c");
        verify(exit).execute("c", router);
    }

    @Test
    public void should_change_accept_commands_to_BarCodingCommand_when_input_a(){
        Command exit = mock(ExitCommand.class);
        Router router =new Router(exit, barCoding, zipCoding);
        router.start();
        String message = router.acceptCommand("a");
        
        assertThat(message).isEqualTo("Please input zipcode. \n");
        assertThat(router.getAcceptableCommands().size()).isEqualTo(1);
        assertThat(router.getAcceptableCommands().get(0)).isOfAnyClassIn(BarCodingCommand.class);
    }
    
    @Test
    public void should_return_to_start_when_input_right(){
        Command exit = mock(ExitCommand.class);
        Router router =new Router(exit, barCoding, zipCoding);
        TranslateMessage t = new TranslateMessage();
        t.setSuccess(true);
        t.setMessage("SuccessMessage");
        when(barCoding.toBarcode(anyString())).thenReturn(t);
        router.start();
        router.acceptCommand("a");
        String message = router.acceptCommand("12345");

        assertThat(message).isEqualTo("SuccessMessage\n"+"I can accept these commands:\n" +
                " a. input zipcode, translate to barcode. \n" +
                " b. input barcode, translate to zipcode. \n" +
                " c. quit. \n" +
                " What is your command? \n");

        assertThat(router.getAcceptableCommands().size()).isEqualTo(3);
    }

    @Test
    public void should_not_return_to_start_when_input_right(){
        Command exit = mock(ExitCommand.class);
        Router router =new Router(exit, barCoding, zipCoding);
        TranslateMessage t = new TranslateMessage();
        t.setSuccess(false);
        t.setMessage("ErrorMessage");
        when(barCoding.toBarcode(anyString())).thenReturn(t);
        router.start();
        router.acceptCommand("a");
        String message = router.acceptCommand("1-2345");

        assertThat(message).isEqualTo("ErrorMessage");

        assertThat(router.getAcceptableCommands().size()).isEqualTo(1);
        assertThat(router.getAcceptableCommands().get(0)).isOfAnyClassIn(BarCodingCommand.class);

    }
    
    
}
