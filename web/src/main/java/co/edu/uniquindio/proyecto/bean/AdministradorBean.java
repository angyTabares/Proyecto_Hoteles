package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.dto.LugarCalificacionDTO;
import co.edu.uniquindio.proyecto.dto.LugaresNoAutDTO;
import co.edu.uniquindio.proyecto.dto.NumeroLugaresPorCategoriaDTO;
import co.edu.uniquindio.proyecto.entidades.Administrador;
import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Moderador;
import co.edu.uniquindio.proyecto.entidades.Persona;
import co.edu.uniquindio.proyecto.servicios.AdministradorServicio;
import co.edu.uniquindio.proyecto.servicios.CiudadServicio;
import co.edu.uniquindio.proyecto.servicios.LugarServicio;
import co.edu.uniquindio.proyecto.servicios.ModeradorServicio;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;


import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;

import org.primefaces.model.charts.hbar.HorizontalBarChartDataSet;
import org.primefaces.model.charts.hbar.HorizontalBarChartModel;
import org.primefaces.model.charts.optionconfig.animation.Animation;
import org.primefaces.model.charts.optionconfig.legend.Legend;
import org.primefaces.model.charts.optionconfig.legend.LegendLabel;
import org.primefaces.model.charts.optionconfig.title.Title;
import org.primefaces.model.charts.pie.PieChartDataSet;
import org.primefaces.model.charts.pie.PieChartModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class AdministradorBean implements Serializable {

    @Getter
    @Setter
    @Value(value = "#{seguridadBean.persona}")
    private Persona personaLogin;

    @Getter
    @Setter
    private PieChartModel pieModel;

    @Getter
    @Setter
    private Ciudad ciudadChart;

    @Getter
    @Setter
    private BarChartModel barModel;

    @Getter
    @Setter
    private List<Ciudad> ciudades;

    @Getter
    @Setter
    private HorizontalBarChartModel hbarModel;

    @Getter
    @Setter
    private List<Moderador> listaModeradores;

    @Getter
    @Setter
    private Moderador moderador;

    @Getter
    @Setter
    private boolean editar;


    private final AdministradorServicio administradorServicio;
    private final ModeradorServicio moderadorServicio;
    private final LugarServicio lugarServicio;
    private final CiudadServicio ciudadServicio;


    public AdministradorBean(AdministradorServicio administradorServicio, ModeradorServicio moderadorServicio, LugarServicio lugarServicio, CiudadServicio ciudadServicio) {
        this.administradorServicio = administradorServicio;

        this.moderadorServicio = moderadorServicio;
        this.lugarServicio = lugarServicio;
        this.ciudadServicio = ciudadServicio;
    }

    @PostConstruct
    public void Inicializar() {
        try {
            this.ciudades = ciudadServicio.listarCiudades();
            this.editar = true;
            this.listaModeradores = moderadorServicio.listarModeradores();
            reporteCantLugaresPorCategoria();
            reporteCalificacionLugaresCiudad(this.ciudades.get(0));
            reporteLugaresNoAutCiudad();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cambiarGraficaCalificacion() {

        System.out.println(this.ciudadChart.getNombre());
        reporteCalificacionLugaresCiudad(this.ciudadChart);

    }


    public void nuevoModerador() {
        this.editar = false;
        System.out.println("creando");
        this.moderador = new Moderador();


    }

    public void cambiarEditar() {
        System.out.println(editar);
        this.editar = true;

    }

    public void crearModerador() {
        try {
            if (this.editar) {
                moderadorServicio.actualizarModerador(this.moderador);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Moderador Actualizado"));
            } else {
                this.moderador.setAdministrador((Administrador) this.personaLogin);
                this.listaModeradores.add(this.moderador);
                this.moderadorServicio.registrarModerador(this.moderador);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Moderador creado"));

            }
            this.moderador = new Moderador();
            PrimeFaces.current().executeScript("PF('manageModeradorDialog').hide()");
            PrimeFaces.current().ajax().update("tabs:form:moderadorMessages");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminarModerador() {
        try {
            System.out.println("Eliminando: " + this.moderador.getNombre());
            this.listaModeradores.remove(this.moderador);
            this.moderadorServicio.eliminarModerador(this.moderador);
            this.moderador = null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Moderador Eliminado"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        PrimeFaces.current().ajax().update("tabs:form:moderadorMessages");
    }


    private void reporteCantLugaresPorCategoria() {

        pieModel = new PieChartModel();
        ChartData data = new ChartData();
        PieChartDataSet dataSet = new PieChartDataSet();
        List<Number> values = new ArrayList<>();
        List<String> labels = new ArrayList<>();
        List<String> colors = new ArrayList<>();

        List<NumeroLugaresPorCategoriaDTO> lista = lugarServicio.cantLugaresPorCategorias();

        for (NumeroLugaresPorCategoriaDTO nlc : lista) {

            values.add(nlc.getCantidadLugares());
            labels.add(nlc.getNombre());
            colors.add(generarColores());
        }
        dataSet.setBackgroundColor(colors);
        dataSet.setData(values);
        data.setLabels(labels);
        data.addChartDataSet(dataSet);
        pieModel.setData(data);
    }

    public void reporteCalificacionLugaresCiudad(Ciudad ciudad) {

        hbarModel = new HorizontalBarChartModel();
        ChartData data = new ChartData();

        HorizontalBarChartDataSet hbarDataSet = new HorizontalBarChartDataSet();
        hbarDataSet.setLabel("Promedio de calificacion de lugares de la ciudad de " + ciudad.getNombre());
        List<Number> values = new ArrayList<>();
        List<String> bgColor = new ArrayList<>();
        List<String> borderColor = new ArrayList<>();
        List<String> labels = new ArrayList<>();

        List<LugarCalificacionDTO> lista = lugarServicio.calificacionesLugarCiudad(ciudad.getCodigo());

        for (LugarCalificacionDTO lc : lista) {

            values.add(lc.getCalificacion());
            labels.add(lc.getLugar().getNombre());
            String color = generarColores();
            bgColor.add(color);
            borderColor.add(color);
        }


        hbarDataSet.setData(values);
        hbarDataSet.setBackgroundColor(bgColor);
        hbarDataSet.setBorderColor(borderColor);
        hbarDataSet.setBorderWidth(1);
        data.addChartDataSet(hbarDataSet);

        data.setLabels(labels);
        hbarModel.setData(data);

        //Options
        BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        linearAxes.setOffset(true);
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        ticks.setBeginAtZero(true);
        linearAxes.setTicks(ticks);
        cScales.addXAxesData(linearAxes);
        options.setScales(cScales);

        Title title = new Title();
        title.setDisplay(true);
        title.setText("Horizontal Bar Chart");
        options.setTitle(title);

        hbarModel.setOptions(options);
    }

    public void reporteLugaresNoAutCiudad() {
        barModel = new BarChartModel();
        ChartData data = new ChartData();

        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel("Ciudad con mas lugares no aprobados");

        List<LugaresNoAutDTO> lista = ciudadServicio.listaLugaresNoAutPorCiudad();

        List<Number> values = new ArrayList<>();
        List<String> bgColor = new ArrayList<>();
        List<String> borderColor = new ArrayList<>();
        List<String> labels = new ArrayList<>();
        for (LugaresNoAutDTO lna : lista) {

            values.add(lna.getCantidad());
            labels.add(lna.getCiudad());
            String color = generarColores();
            bgColor.add(color);
            borderColor.add(color);
        }


        barDataSet.setData(values);
        barDataSet.setBackgroundColor(bgColor);
        barDataSet.setBorderColor(borderColor);
        barDataSet.setBorderWidth(1);

        data.addChartDataSet(barDataSet);
        data.setLabels(labels);
        barModel.setData(data);

        //Options
        BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        linearAxes.setOffset(true);
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        ticks.setBeginAtZero(true);
        linearAxes.setTicks(ticks);
        cScales.addYAxesData(linearAxes);
        options.setScales(cScales);

        Title title = new Title();
        title.setDisplay(true);
        title.setText("Bar Chart");
        options.setTitle(title);

        Legend legend = new Legend();
        legend.setDisplay(true);
        legend.setPosition("top");
        LegendLabel legendLabels = new LegendLabel();
        legendLabels.setFontStyle("bold");
        legendLabels.setFontColor("#2980B9");
        legendLabels.setFontSize(24);
        legend.setLabels(legendLabels);
        options.setLegend(legend);

        // disable animation
        Animation animation = new Animation();
        animation.setDuration(0);
        options.setAnimation(animation);
        barModel.setOptions(options);
    }

    private static String generarColores() {
        int r = (int) (Math.random() * 255) + 1;
        int g = (int) (Math.random() * 255) + 1;
        int b = (int) (Math.random() * 255) + 1;

        return "rgb(" + r + "," + g + "," + b + ")";
    }


}
