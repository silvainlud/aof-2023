package fr.silvain.AdventOfCode.jour.jour8;

import fr.silvain.AdventOfCode.Jour;

import java.util.*;
import java.util.stream.Collectors;

public class Jour8 extends Jour {
    record Node(String name, String left, String right, int distance) {
    }

    public Map<String, Node> parse(List<String> in) {
        Map<String, Node> map = new HashMap<>();
        for (String s : in) {
            if (!s.contains("=")) continue;
            String[] split = s.split("=");
            String name = split[0].trim();

            String[] leftRight = split[1].replace("(", "").replace(")", "").split(",");
            String source = leftRight[0].trim();
            String destination = leftRight[1].trim();
            int distance = 0;
            map.putIfAbsent(name, new Node(name, source, destination, distance));
        }
        return map;
    }

    public boolean isStartNode(Node node) {
        return node.name.endsWith("A");
    }

    public boolean isEndNode(Node node) {
        return node.name.endsWith("Z");
    }

    @Override

    public String executePartie1(List<String> in) {
        Map<String, Node> map = parse(in);
        String currentNode = "AAA";
        final char[] instructions = in.get(0).toCharArray();
        final String dest = "ZZZ";
        int currentStep = 0;
        while (!currentNode.equals(dest)) {
            Node node = map.get(currentNode);
            if (instructions[currentStep % instructions.length] == 'R') {
                currentNode = node.right;
            } else {
                currentNode = node.left;
            }
            currentStep++;
        }
        return String.valueOf(currentStep);
    }

    @Override
    public String executePartie2(List<String> in) {
        Map<String, Node> map = parse(in);
        final char[] instructions = in.get(0).toCharArray();

        List<Node> currentNodes = map.entrySet().stream().filter(x -> isStartNode(x.getValue())).map(Map.Entry::getValue).toList();

        List<Integer> finishedStep = new ArrayList<>();
        int currentStep = 0;
        while (true) {
            final int currentInstruction = instructions[currentStep % instructions.length];
            List<Node> newNodes = new ArrayList<>();
            int finalCurrentStep = currentStep;
            currentNodes.forEach(x -> {
                if (isEndNode(x)) {
                    finishedStep.add(finalCurrentStep);
                } else if (currentInstruction == 'R') {
                    newNodes.add(map.get(x.right));
                } else {
                    newNodes.add(map.get(x.left));
                }
            });

            currentNodes = newNodes;
            if (newNodes.isEmpty()){
                return String.valueOf(lcm_of_array_elements(finishedStep.stream().mapToInt(Integer::valueOf).toArray()));
            }
            currentStep++;
        }
    }

    public static long lcm_of_array_elements(int[] element_array)
    {
        long lcm_of_array_elements = 1;
        int divisor = 2;

        while (true) {
            int counter = 0;
            boolean divisible = false;

            for (int i = 0; i < element_array.length; i++) {

                // lcm_of_array_elements (n1, n2, ... 0) = 0.
                // For negative number we convert into
                // positive and calculate lcm_of_array_elements.

                if (element_array[i] == 0) {
                    return 0;
                }
                else if (element_array[i] < 0) {
                    element_array[i] = element_array[i] * (-1);
                }
                if (element_array[i] == 1) {
                    counter++;
                }

                // Divide element_array by devisor if complete
                // division i.e. without remainder then replace
                // number with quotient; used for find next factor
                if (element_array[i] % divisor == 0) {
                    divisible = true;
                    element_array[i] = element_array[i] / divisor;
                }
            }

            // If divisor able to completely divide any number
            // from array multiply with lcm_of_array_elements
            // and store into lcm_of_array_elements and continue
            // to same divisor for next factor finding.
            // else increment divisor
            if (divisible) {
                lcm_of_array_elements = lcm_of_array_elements * divisor;
            }
            else {
                divisor++;
            }

            // Check if all element_array is 1 indicate
            // we found all factors and terminate while loop.
            if (counter == element_array.length) {
                return lcm_of_array_elements;
            }
        }
    }
}
