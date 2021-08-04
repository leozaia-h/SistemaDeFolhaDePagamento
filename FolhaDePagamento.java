import java.util.Calendar;

public class FolhaDePagamento {
    
    public int hoje() {
        Calendar c = Calendar.getInstance();
        int dia = c.get(Calendar.DAY_OF_MONTH);
        
        return dia;
    }

    public int diasMes() {
        Calendar c = Calendar.getInstance();
        int diasMes = c.getActualMaximum(Calendar.DAY_OF_MONTH);

        return diasMes;
    }

    public int lastMonthDay() {
        Calendar c = Calendar.getInstance();
        int aux = c.getActualMaximum(Calendar.DAY_OF_MONTH);

        System.out.printf("%d\n", aux);
        /**
        if(c.get(aux) == Calendar.SUNDAY) {
            aux -= 2;
        } */
        if(c.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
            aux -= 1;
        }
        
        return aux;
    }

    public int getUltimoDiaUtilDoMes() {
        Calendar calendar = Calendar.getInstance();
        //muda a data da variável para o último dia do mês
        calendar.add(Calendar.MONTH, 1);  
        calendar.set(Calendar.DAY_OF_MONTH, 1);  
        calendar.add(Calendar.DATE, -1);
        //enquanto for sábado, domingo ou feriado
        while(calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY ||
                calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || 
                ehFeriado(calendar)) {
            //decrementa a data em um dia
            calendar.add(Calendar.DATE, -1);            
        }
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public boolean ehFeriado(Calendar calendar) {
        Calendar feriado = Calendar.getInstance();
        //considerando 30 de abril como feriado
        feriado.set(calendar.get(Calendar.YEAR), Calendar.APRIL, 30);
        if(calendar.get(Calendar.DAY_OF_YEAR) == feriado.get(Calendar.DAY_OF_YEAR)) {
            return true;
        }
        else {
            return false;
        }
    }
}
