package visualization.charts;

import static nn.algebra.Alg.*;

import db.dao.DAOSingleton;
import db.dao.GdpDAO;
import model.AnnualGDPelement;
import model.Country;
import nn.data.*;
import nn.perceptrons.LinearOneLayerPerceptron;
import nn.perceptrons.LinearPerceptronTrainer;
import nn.perceptrons.NeuralNetwork;
import nn.perceptrons.Trainer;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import java.sql.SQLException;
import java.util.List;

public class PlotGDP extends ApplicationFrame
{
    public PlotGDP( String applicationTitle , String chartTitle )
    {
        super(applicationTitle);
        JFreeChart lineChart = ChartFactory.createLineChart(
                chartTitle,
                "Years","Real GDP",
                //createDataset(),
                predictionsDataset(),
                PlotOrientation.VERTICAL,
                true,true,false);
        JFreeChart lineChart1 = ChartFactory.createLineChart(
                chartTitle,
                "Years","Real GDP",
                //createDataset(),
                predictionsDataset1(),
                PlotOrientation.VERTICAL,
                true,true,false);

        ChartPanel chartPanel = new ChartPanel( lineChart );
        ChartPanel chartPanel1 = new ChartPanel( lineChart1 );
        chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
        setContentPane( chartPanel );
        setContentPane( chartPanel1 );

    }

    private DefaultCategoryDataset createDataset( )
    {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
        GdpDAO gdpDAO = DAOSingleton.getInstance().getGdpDAO();
        try {
            List<AnnualGDPelement> annualGDPelements = gdpDAO.getAnnualGDP(Country.USA, 1929, 2016);
            for (AnnualGDPelement e: annualGDPelements) {
                dataset.addValue( e.getChainedGDP() , "USA", Integer.toString(e.getYear()) );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dataset;
    }

    private DefaultCategoryDataset predictionsDataset( )
    {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
        GdpDAO gdpDAO = DAOSingleton.getInstance().getGdpDAO();
        NeuralNetwork nn = new LinearOneLayerPerceptron(rand(3, 1));
        try {
            List<AnnualGDPelement> annualGDPelements = gdpDAO.getAnnualGDP(Country.USA, 1929, 2016);
            RawData rawData = new GdpRawDataAdditionFearures(annualGDPelements);
            TrainingData trainingData = rawData.toTrainingData();
            Trainer coach = new LinearPerceptronTrainer(nn, trainingData);
            coach.train();
            double[][] x = trainingData.x;
            double[][] pred = new double[x.length][1];
            for (int i = 0; i < x.length; i++) {
                pred[i] = nn.predict(x[i]);
                System.out.println(pred[i][0]);
                //dataset.addValue( trainingData.y[i][0] - pred[i][0] , "USA", Double.toString(x[i][1]) );
                dataset.addValue(trainingData.y[i][0] , "USA", Double.toString(x[i][1]));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dataset;
    }

    private DefaultCategoryDataset predictionsDataset1( )
    {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
        GdpDAO gdpDAO = DAOSingleton.getInstance().getGdpDAO();
        NeuralNetwork nn = new LinearOneLayerPerceptron(rand(3, 1));
        try {
            List<AnnualGDPelement> annualGDPelements = gdpDAO.getAnnualGDP(Country.USA, 1929, 2016);
            RawData rawData = new GdpRawDataAdditionFearures(annualGDPelements);
            TrainingData trainingData = rawData.toTrainingData();
            Trainer coach = new LinearPerceptronTrainer(nn, trainingData);
            coach.train();
            double[][] x = trainingData.x;
            double[][] pred = new double[x.length][1];
            for (int i = 0; i < x.length; i++) {
                pred[i] = nn.predict(x[i]);
                System.out.println(pred[i][0]);
                dataset.addValue(pred[i][0] , "USA", Double.toString(x[i][1]));
                //dataset.addValue(trainingData.y[i][0] , "USA", Double.toString(x[i][1]));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dataset;
    }

    public static void main( String[ ] args )
    {
        PlotGDP chart = new PlotGDP(
                "GDP show" ,
                "GDP");

        chart.pack( );
        RefineryUtilities.centerFrameOnScreen( chart );
        chart.setVisible( true );
    }
}
