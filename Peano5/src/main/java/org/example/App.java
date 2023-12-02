package org.example;

import org.davidmoten.hilbert.HilbertCurve;
import org.openimaj.feature.local.list.LocalFeatureList;
import org.openimaj.feature.local.matcher.BasicMatcher;
import org.openimaj.feature.local.matcher.FastBasicKeypointMatcher;
import org.openimaj.feature.local.matcher.LocalFeatureMatcher;
import org.openimaj.feature.local.matcher.MatchingUtilities;
import org.openimaj.feature.local.matcher.consistent.ConsistentLocalFeatureMatcher2d;
import org.openimaj.image.DisplayUtilities;
import org.openimaj.image.ImageUtilities;
import org.openimaj.image.MBFImage;
import org.openimaj.image.colour.ColourSpace;
import org.openimaj.image.colour.RGBColour;
import org.openimaj.image.feature.local.engine.DoGSIFTEngine;
import org.openimaj.image.feature.local.keypoints.Keypoint;
import org.openimaj.image.processing.convolution.FGaussianConvolve;
import org.openimaj.image.typography.hershey.HersheyFont;
import org.openimaj.math.geometry.transforms.estimation.RobustAffineTransformEstimator;
import org.openimaj.math.model.fit.RANSAC;

import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

/**
 * OpenIMAJ Hello world!
 *
 */
public class App {
    public static void main( String[] args ) throws IOException {
    	//Create an image
        MBFImage image = new MBFImage(320,70, ColourSpace.RGB);
        //Fill the image with white
        image.fill(RGBColour.WHITE);
        		        
        //Render some test into the image
        image.drawText("Hello World", 10, 60, HersheyFont.CURSIVE, 50, RGBColour.BLACK);

        MBFImage query = ImageUtilities.readMBF(new URL("http://static.openimaj.org/media/tutorial/query.jpg"));
        MBFImage target = ImageUtilities.readMBF(new URL("http://static.openimaj.org/media/tutorial/target.jpg"));

        DoGSIFTEngine engine = new DoGSIFTEngine();
        LocalFeatureList<Keypoint> queryKeypoints = engine.findFeatures(query.flatten());
        LocalFeatureList<Keypoint> targetKeypoints = engine.findFeatures(target.flatten());

        Keypoint a = queryKeypoints.get(0);

        byte[] test = a.ivec;

        HilbertCurve c = HilbertCurve.bits(5).dimensions(128);
        BigInteger index = c.index(3, 4);

        /**
         * The Keypoint class contain a public field called ivec which,
         * in the case of a standard SIFT descriptor is a 128 dimensional description
         * of a patch of pixels around a detected point. Various distance measures can be used to compare Keypoints to Keypoints.
         */


        System.out.println(Arrays.toString(a.ivec));



    }
}
