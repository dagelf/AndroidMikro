package br.com.ineedsolutions.androidmikro;

import me.legrange.mikrotik.MikrotikApiException;


public class SimpleCommand extends Example {
    
        public static void main(String...args) throws Exception {
            SimpleCommand ex = new SimpleCommand();
            ex.connect();
            ex.test();
            ex.disconnect();
        }
        
        private void test() throws MikrotikApiException {
            con.execute("/system/reboot");
        }

   
}
