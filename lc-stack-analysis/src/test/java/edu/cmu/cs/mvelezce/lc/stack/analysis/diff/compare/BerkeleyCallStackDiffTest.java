package edu.cmu.cs.mvelezce.lc.stack.analysis.diff.compare;

import com.github.difflib.algorithm.DiffException;
import edu.cmu.cs.mvelezce.adapters.measureDiskOrderedScan.BaseMeasureDiskOrderedScanAdapter;
import org.junit.Test;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class BerkeleyCallStackDiffTest {

  @Test
  public void log_TEMPORARY_FALSE() throws IOException, DiffException {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;

    String option1 = "TEMPORARY";
    Set<String> stacks1 = new HashSet<>();
    //    stacks1.add("a9718464-f0a2-4b7f-b220-623c5a53550e.csv");
    //    stacks1.add("9474cede-10f4-47d4-83c5-f018a39b0f23.csv");
    //    stacks1.add("099558c5-dbc6-4d7f-b858-6e313228ffb4.csv");
    //    stacks1.add("dab033f1-3922-4fab-979f-43d3f9a134d9.csv");
    //    stacks1.add("5963f653-9b2d-47e6-9a19-ae1e64d5294e.csv");
    //    stacks1.add("2b23bbcf-e73c-48bc-83de-b8c0a332e5b7.csv");
    //    stacks1.add("e1c30d85-5c2f-430d-85b5-742b14b99eaa.csv");
    //    stacks1.add("7b3cf48d-2bc1-4466-94fd-30b8702c07da.csv");
    //    stacks1.add("47542774-958e-4d82-a96a-0b0d01c702e8.csv");
    //    stacks1.add("e62e263f-fa3f-4d8d-ac5c-721dfc5f1fde.csv");
    //    stacks1.add("96848a3d-1b8b-47b8-8be8-116c343c28f2.csv");
    //    stacks1.add("84237eda-18bf-453f-bfda-6f64a40daec1.csv");
    //    stacks1.add("24715a69-d268-4e26-a7cd-29375a7e9bf3.csv");
    //    stacks1.add("9efb9e0b-8ecd-4e0c-b1c7-88b7df472dd8.csv");
    //    stacks1.add("4546e4cd-2449-4833-b6bf-a7c82c1b1324.csv");
    //    stacks1.add("65022d59-268f-4556-a451-fc355d0fde03.csv");
    //    stacks1.add("98afd8a0-5333-42ec-b6f5-aaad9e246fcc.csv");
    //    stacks1.add("45072bd5-0ecb-4aad-bba5-35b7d60f67bb.csv");
    //    stacks1.add("30efdfdf-69c1-4c1e-aa29-e5d3347bb579.csv");
    //    stacks1.add("18a86ab9-6986-425d-9dcb-b2da7e465a10.csv");
    //    stacks1.add("5ab5bfe2-665b-4b8b-b84a-c87ddf2e9f5d.csv");
    //    stacks1.add("5b92a94b-dcff-4034-bc0f-4f99ea7e72bf.csv");
    //    stacks1.add("8712ee32-ed67-406a-bc73-136ce7fa719d.csv");
    //    stacks1.add("30b4321b-5375-413f-a8f7-3d82a3754c60.csv");
    //    stacks1.add("a51330e1-164c-477f-b9ac-c0eba551db94.csv");
    //    stacks1.add("2dad278e-c54a-49ce-be64-715bd33325d5.csv");
    //    stacks1.add("667e25df-f19d-4c8b-96b9-010b838baba8.csv");
    //    stacks1.add("5e232f61-62bf-4391-b414-b45ec3ded0ce.csv");
    //    stacks1.add("b748a7e2-10f8-44e1-9968-6691b3a0266e.csv");
    //    stacks1.add("d47d6653-ea77-4527-b5c4-c04a1fe008de.csv");
    //    stacks1.add("d0939a20-1036-4026-9513-bb5a21f7f9d9.csv");
    //    stacks1.add("3a781a52-31bc-4cb1-bb44-673396346a38.csv");
    //    stacks1.add("1cffb7b6-069a-4978-bff2-c9d9e33de40b.csv");
    //    stacks1.add("356e1684-5c24-49c9-afc4-d7ab06f79246.csv");
    //    stacks1.add("d3606e87-c1a6-4463-be68-81b9d278c597.csv");
    //    stacks1.add("270747b9-2646-44e7-96b4-6372357bcc3f.csv");
    //    stacks1.add("3cbb3bb1-aa30-4a29-a46b-d1b4760c2f1c.csv");
    //    stacks1.add("7b793d40-9631-484d-a0a2-f1a166faaa2c.csv");
    //    stacks1.add("7ae63f3c-0a91-4853-bb38-7113875bd2e9.csv");
    //    stacks1.add("e9d1e448-28ed-48ec-b82f-898940476687.csv");
    //    stacks1.add("071b71bc-e4c9-4f9c-9109-f06c2ce9c453.csv");
    //    stacks1.add("36d6eb73-04ae-46dd-9c6f-1d95f2010a54.csv");
    //    stacks1.add("ade32b1c-b59b-48ea-b500-13b47f986cef.csv");
    //    stacks1.add("c41e3852-5952-49c4-9465-33e61dd3617d.csv");
    //    stacks1.add("05c3b5a6-0ca4-40eb-8ab2-464e590fbba2.csv");
    //    stacks1.add("18e5c8ee-e11e-4a0b-8b29-e27c1c57f016.csv");
    //    stacks1.add("3c82741a-8ba1-4f8e-b21b-b85184898cf5.csv");
    //    stacks1.add("b789faa7-4641-44f2-a094-fdd1e32196ec.csv");
    //    stacks1.add("b0c4a8da-ded6-4d38-9b0b-4f31beb55957.csv");

    String option2 = "FALSE";
    Set<String> stacks2 = new HashSet<>();
    //    stacks2.add("9a9491f4-c3f3-41a5-b49a-55f357ffa633.csv");
    //    stacks2.add("a0a3ff89-92c3-42ef-8715-e276a4d18ef0.csv");
    //    stacks2.add("464e5bd8-b5c8-444c-a880-902b48809133.csv");
    //    stacks2.add("438d863e-ff2d-42e6-ba1c-d423c6e7892b.csv");
    //    stacks2.add("414aa048-36d2-4658-8555-c984d6dcbf40.csv");
    //    stacks2.add("bc52f70a-2c04-4276-b357-1f06ce600f3f.csv");
    //    stacks2.add("744da746-2f9d-4978-9c24-620afc8a1129.csv");
    //    stacks2.add("c19e2b6d-8ddc-4c8a-9bfb-fc7a3ac2bb95.csv");
    //    stacks2.add("ac3eca6e-469d-420b-a45f-2f4224142267.csv");
    //    stacks2.add("f81357a5-e11d-4860-a501-0bb259beb2fc.csv");
    //    stacks2.add("238334f5-948c-46c0-a9a5-4dc6f3c021f5.csv");
    //    stacks2.add("fb599616-cac5-4275-804b-ff926a32084c.csv");
    //    stacks2.add("4f068e65-9f35-453a-b763-b53c600e1c26.csv");
    //    stacks2.add("24ff5d60-5d8e-4526-baa8-fa07aefc0e7a.csv");
    //    stacks2.add("6a79b60d-532f-4238-8344-b38075f368ae.csv");
    //    stacks2.add("8502cd1e-ca10-4ef4-8fed-7dd1143fe863.csv");
    //    stacks2.add("3cf06d12-4a85-4723-8375-7c37d1b60168.csv");
    //    stacks2.add("f1608c5d-1acf-4715-bece-c5a3d85175c8.csv");
    //    stacks2.add("3a6a33f6-0c0d-429c-9853-e2b206066ff6.csv");
    //    stacks2.add("58c85d01-2ca6-45ca-80c4-49fa0672b2e0.csv");
    //    stacks2.add("0323f017-c3ef-4684-81f2-6f2a2fd96b97.csv");
    //    stacks2.add("2751c083-44af-4638-9067-978efe8de7de.csv");
    //    stacks2.add("719448d0-ae8f-4827-8964-02f2acba9bac.csv");
    //    stacks2.add("4b84b775-593a-4261-b008-ea90f0140b01.csv");
    //    stacks2.add("e13d1ed2-cbda-4b60-a4c7-e3f0472ab71f.csv");
    //    stacks2.add("3ef22ab5-ac5c-4d7d-8d9f-641e62a576da.csv");
    //    stacks2.add("c411d5f7-3234-4bfd-964a-1f819665cbca.csv");
    //    stacks2.add("3971a596-931a-4102-896b-18a3b207b4e8.csv");
    //    stacks2.add("6258d70d-e37a-455c-b7be-b58edad2b495.csv");
    //    stacks2.add("d32f0293-ab8d-4c56-ba1d-abf698d8cce9.csv");
    //    stacks2.add("23102ac1-c9d9-4f5f-a64d-46df0d809771.csv");
    //    stacks2.add("0cc5cde3-caa5-4103-a415-07a8416f70ca.csv");
    //    stacks2.add("2f32a9b0-0cbd-4416-aef0-7e19ba6e039c.csv");
    //    stacks2.add("8e5b222a-2b81-49b2-8266-36884cd029d9.csv");
    //    stacks2.add("eb8651ad-bb09-4d5e-9d55-185ee0717c7d.csv");
    //    stacks2.add("ab29c314-63c6-4d30-a374-15b1f6e51fec.csv");
    //    stacks2.add("f887fff8-ea2e-4fa4-b72d-0190bc009ff3.csv");
    //    stacks2.add("b84a0481-33bb-4c54-9f72-dae06c430561.csv");
    //    stacks2.add("e0b2323c-44b4-44b7-aa1f-cdca822dc6dc.csv");
    //    stacks2.add("3c7f2253-d677-42a2-a929-e2d708bbc492.csv");
    //    stacks2.add("40f677a0-4f40-4c0c-ac2f-9d5b0775d5b9.csv");
    //    stacks2.add("9560022c-e71f-4c3f-8f26-335718b4605c.csv");
    //    stacks2.add("e114a041-b6fe-4db5-98a0-02be1027339b.csv");
    //    stacks2.add("822d7ad3-b3a8-4072-85d8-6606dbdf3f79.csv");
    //    stacks2.add("0b67a732-dfb8-433d-a046-d8a4b145a6d0.csv");
    //    stacks2.add("29a84058-4a17-4843-92ec-750b5ea25559.csv");
    //    stacks2.add("0b3dddbd-8869-4698-a88c-05b647bb937d.csv");
    //    stacks2.add("00c098ad-7c05-42ad-9a56-1c59a429a931.csv");
    //    stacks2.add("7a2051e5-4b33-40c8-9443-451ff54f8e51.csv");
    //    stacks2.add("fc2d078d-6df0-4823-9b60-e17b19a36c50.csv");
    //    stacks2.add("2cd72f2c-947d-4856-8d7c-6be722db1578.csv");

    CallStackDiff differ =
        new CallStackDiff(
            programName,
            1,
            option1,
            stacks1,
            option2,
            stacks2,
            "com.sleepycat.je.log.LogManager",
            "log",
            "(Lcom/sleepycat/je/log/LogParams;)Lcom/sleepycat/je/log/LogItem;");
    differ.diff();
  }

  @Test
  public void addPostMarshallingInfo_TEMPORARY_FALSE_1() throws IOException, DiffException {
    // no overwrite, end move cursor
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;

    String option1 = "TEMPORARY";
    Set<String> stacks1 = new HashSet<>();
    stacks1.add("cc283d4f-f228-4a40-9f52-6f5d097c6b70.csv");

    String option2 = "FALSE";
    Set<String> stacks2 = new HashSet<>();
    stacks2.add("ca3e4963-2a92-4ff8-be27-379594698720.csv");

    CallStackDiff differ =
        new CallStackDiff(
            programName,
            1,
            option1,
            stacks1,
            option2,
            stacks2,
            "com.sleepycat.je.log.LogEntryHeader",
            "addPostMarshallingInfo",
            "(Ljava/nio/ByteBuffer;JLcom/sleepycat/je/utilint/VLSN;)Ljava/nio/ByteBuffer;");
    differ.diff();
  }

  @Test
  public void addPostMarshallingInfo_TEMPORARY_FALSE_2() throws IOException, DiffException {
    // no overwrite, end move cursor
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;

    String option1 = "TEMPORARY";
    Set<String> stacks1 = new HashSet<>();
    stacks1.add("a309943a-40d8-48eb-b99b-9e2b5b4a4117.csv");

    String option2 = "FALSE";
    Set<String> stacks2 = new HashSet<>();
    stacks2.add("2e4ae861-b290-4548-8a4c-b5ec2ea6a364.csv");

    CallStackDiff differ =
        new CallStackDiff(
            programName,
            2,
            option1,
            stacks1,
            option2,
            stacks2,
            "com.sleepycat.je.log.LogEntryHeader",
            "addPostMarshallingInfo",
            "(Ljava/nio/ByteBuffer;JLcom/sleepycat/je/utilint/VLSN;)Ljava/nio/ByteBuffer;");
    differ.diff();
  }

  @Test
  public void addPostMarshallingInfo_TEMPORARY_FALSE_3() throws IOException, DiffException {
    // no overwrite, special eviction vs process target
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;

    String option1 = "TEMPORARY";
    Set<String> stacks1 = new HashSet<>();
    stacks1.add("f3fa9d0b-5e29-4006-8dfe-c0f0eff49b6e.csv");

    String option2 = "FALSE";
    Set<String> stacks2 = new HashSet<>();
    stacks2.add("2e4ae861-b290-4548-8a4c-b5ec2ea6a364.csv");

    CallStackDiff differ =
        new CallStackDiff(
            programName,
            3,
            option1,
            stacks1,
            option2,
            stacks2,
            "com.sleepycat.je.log.LogEntryHeader",
            "addPostMarshallingInfo",
            "(Ljava/nio/ByteBuffer;JLcom/sleepycat/je/utilint/VLSN;)Ljava/nio/ByteBuffer;");
    differ.diff();
  }

  @Test
  public void addPostMarshallingInfo_TEMPORARY_FALSE_4() throws IOException, DiffException {
    // put, close vs put internal
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;

    String option1 = "TEMPORARY";
    Set<String> stacks1 = new HashSet<>();
    stacks1.add("5810b3bd-7869-4f5a-b895-b280fbad6ad7.csv");

    String option2 = "FALSE";
    Set<String> stacks2 = new HashSet<>();
    stacks2.add("dacc7903-29d2-49ed-bb29-7b1a3e4717e3.csv");
    stacks2.add("d6429d1c-ebd8-40f4-88ac-db1bf349284a.csv");
    stacks2.add("ae1d17de-5e17-4172-b69a-62b37a4f0065.csv");
    stacks2.add("99ea8f65-b9e1-40c0-81b1-dde4ec3e9685.csv");
    stacks2.add("ca3e4963-2a92-4ff8-be27-379594698720.csv");

    CallStackDiff differ =
        new CallStackDiff(
            programName,
            4,
            option1,
            stacks1,
            option2,
            stacks2,
            "com.sleepycat.je.log.LogEntryHeader",
            "addPostMarshallingInfo",
            "(Ljava/nio/ByteBuffer;JLcom/sleepycat/je/utilint/VLSN;)Ljava/nio/ByteBuffer;");
    differ.diff();
  }

  @Test
  public void addPostMarshallingInfo_TEMPORARY_FALSE_5() throws IOException, DiffException {
    // put no overwrite, close vs put internal
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;

    String option1 = "TEMPORARY";
    Set<String> stacks1 = new HashSet<>();
    stacks1.add("9fa0342f-c9f6-41a3-acfc-8bde6f643970.csv");

    String option2 = "FALSE";
    Set<String> stacks2 = new HashSet<>();
    stacks2.add("2e4ae861-b290-4548-8a4c-b5ec2ea6a364.csv");
    stacks2.add("9f069bdd-1022-4fef-9733-7a9ddc54b608.csv");

    CallStackDiff differ =
        new CallStackDiff(
            programName,
            5,
            option1,
            stacks1,
            option2,
            stacks2,
            "com.sleepycat.je.log.LogEntryHeader",
            "addPostMarshallingInfo",
            "(Ljava/nio/ByteBuffer;JLcom/sleepycat/je/utilint/VLSN;)Ljava/nio/ByteBuffer;");
    differ.diff();
  }

  @Test
  public void readFromFileInternal_TEMPORARY_FALSE_1() throws IOException, DiffException {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;

    String option1 = "TEMPORARY";
    Set<String> stacks1 = new HashSet<>();
    stacks1.add("b645b720-334a-4430-8336-91484944142e.csv");

    String option2 = "FALSE";
    Set<String> stacks2 = new HashSet<>();
    stacks2.add("70612cf7-aec1-4778-8144-e7f40bf8e574.csv");

    stacks2.add("fc890311-1c12-4358-a86b-a1f4a9b589e3.csv");
    stacks2.add("9dfda6ff-44f2-4f43-ba4b-ae73b037d78c.csv");
    stacks2.add("e5e1c44a-03dc-4bd6-adaa-fe36ff2a964e.csv");
    stacks2.add("b9624840-2b07-4acc-8a85-f6548e5b9813.csv");

    CallStackDiff differ =
        new CallStackDiff(
            programName,
            1,
            option1,
            stacks1,
            option2,
            stacks2,
            "com.sleepycat.je.log.FileManager",
            "readFromFileInternal",
            "(Ljava/io/RandomAccessFile;Ljava/nio/ByteBuffer;JJ)V");
    differ.diff();
  }

  @Test
  public void readFromFileInternal_TEMPORARY_FALSE_2() throws IOException, DiffException {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;

    String option1 = "TEMPORARY";
    Set<String> stacks1 = new HashSet<>();
    stacks1.add("7d5d10ff-0f0f-4bdf-9c43-2ebed1ab30ad.csv");

    String option2 = "FALSE";
    Set<String> stacks2 = new HashSet<>();
    stacks2.add("bfe8cf4e-39be-4ee4-8be9-5c0ec1f576a6.csv");

    stacks2.add("fc890311-1c12-4358-a86b-a1f4a9b589e3.csv");
    stacks2.add("9dfda6ff-44f2-4f43-ba4b-ae73b037d78c.csv");
    stacks2.add("e5e1c44a-03dc-4bd6-adaa-fe36ff2a964e.csv");
    stacks2.add("b9624840-2b07-4acc-8a85-f6548e5b9813.csv");

    CallStackDiff differ =
        new CallStackDiff(
            programName,
            2,
            option1,
            stacks1,
            option2,
            stacks2,
            "com.sleepycat.je.log.FileManager",
            "readFromFileInternal",
            "(Ljava/io/RandomAccessFile;Ljava/nio/ByteBuffer;JJ)V");
    differ.diff();
  }

  @Test
  public void readFromFileInternal_TEMPORARY_FALSE_3() throws IOException, DiffException {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;

    String option1 = "TEMPORARY";
    Set<String> stacks1 = new HashSet<>();
    stacks1.add("c0d1868f-b3fe-4dbd-9914-658d7447564f.csv");

    String option2 = "FALSE";
    Set<String> stacks2 = new HashSet<>();
    stacks2.add("fc890311-1c12-4358-a86b-a1f4a9b589e3.csv");
    stacks2.add("9dfda6ff-44f2-4f43-ba4b-ae73b037d78c.csv");
    stacks2.add("e5e1c44a-03dc-4bd6-adaa-fe36ff2a964e.csv");
    stacks2.add("b9624840-2b07-4acc-8a85-f6548e5b9813.csv");

    CallStackDiff differ =
        new CallStackDiff(
            programName,
            3,
            option1,
            stacks1,
            option2,
            stacks2,
            "com.sleepycat.je.log.FileManager",
            "readFromFileInternal",
            "(Ljava/io/RandomAccessFile;Ljava/nio/ByteBuffer;JJ)V");
    differ.diff();
  }

  @Test
  public void writeToFile_TEMPORARY_FALSE_1() throws IOException, DiffException {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;

    String option1 = "TEMPORARY";
    Set<String> stacks1 = new HashSet<>();
    stacks1.add("4e1c0fee-045e-4887-99ad-c0aa95116f6b.csv");

    String option2 = "FALSE";
    Set<String> stacks2 = new HashSet<>();
    stacks2.add("b36be923-4c00-49e4-8f19-899a1fbd17cb.csv");

    CallStackDiff differ =
        new CallStackDiff(
            programName,
            1,
            option1,
            stacks1,
            option2,
            stacks2,
            "com.sleepycat.je.log.FileManager",
            "writeToFile",
            "(Ljava/io/RandomAccessFile;Ljava/nio/ByteBuffer;JJZ)I");
    differ.diff();
  }

  @Test
  public void writeToFile_TEMPORARY_FALSE_2() throws IOException, DiffException {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;

    String option1 = "TEMPORARY";
    Set<String> stacks1 = new HashSet<>();
    stacks1.add("29d14dcb-a346-4c35-b848-b7e1b7712866.csv");

    String option2 = "FALSE";
    Set<String> stacks2 = new HashSet<>();
    stacks2.add("baf4e43d-5024-456c-b87f-8415604bde0f.csv");

    CallStackDiff differ =
        new CallStackDiff(
            programName,
            2,
            option1,
            stacks1,
            option2,
            stacks2,
            "com.sleepycat.je.log.FileManager",
            "writeToFile",
            "(Ljava/io/RandomAccessFile;Ljava/nio/ByteBuffer;JJZ)I");
    differ.diff();
  }

  @Test
  public void writeToFile_TEMPORARY_FALSE_3() throws IOException, DiffException {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;

    String option1 = "TEMPORARY";
    Set<String> stacks1 = new HashSet<>();
    stacks1.add("b28ca0fb-ee7b-4caa-8cb8-bbd735429d3e.csv");

    String option2 = "FALSE";
    Set<String> stacks2 = new HashSet<>();
    stacks2.add("0b3e9166-c6ab-40a2-8a1f-3673bddfc536.csv");

    CallStackDiff differ =
        new CallStackDiff(
            programName,
            3,
            option1,
            stacks1,
            option2,
            stacks2,
            "com.sleepycat.je.log.FileManager",
            "writeToFile",
            "(Ljava/io/RandomAccessFile;Ljava/nio/ByteBuffer;JJZ)I");
    differ.diff();
  }

  @Test
  public void writeToFile_TEMPORARY_FALSE_4() throws IOException, DiffException {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;

    String option1 = "TEMPORARY";
    Set<String> stacks1 = new HashSet<>();
    stacks1.add("410fa099-1f4a-411c-a6e3-8cd3887d9241.csv");

    String option2 = "FALSE";
    Set<String> stacks2 = new HashSet<>();
    stacks2.add("e2e98772-ed95-4ba8-b9f2-f9a2b7a2faf7.csv");
    stacks2.add("43cf293a-46bf-434a-9d30-69c66ccb40e5.csv");
    stacks2.add("899cb9fa-c733-4879-b91d-91afd6fa0bb0.csv");
    stacks2.add("2a08d548-2895-42a0-937e-d88c41256996.csv");
    stacks2.add("bb5d43c3-23e2-453b-822f-80aea599be93.csv");

    CallStackDiff differ =
        new CallStackDiff(
            programName,
            4,
            option1,
            stacks1,
            option2,
            stacks2,
            "com.sleepycat.je.log.FileManager",
            "writeToFile",
            "(Ljava/io/RandomAccessFile;Ljava/nio/ByteBuffer;JJZ)I");
    differ.diff();
  }

  @Test
  public void writeToFile_TEMPORARY_FALSE_5() throws IOException, DiffException {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;

    String option1 = "TEMPORARY";
    Set<String> stacks1 = new HashSet<>();
    stacks1.add("7f1a1fbd-232a-40a5-8bf9-2461186b88aa.csv");

    String option2 = "FALSE";
    Set<String> stacks2 = new HashSet<>();
    stacks2.add("e2e98772-ed95-4ba8-b9f2-f9a2b7a2faf7.csv");
    stacks2.add("43cf293a-46bf-434a-9d30-69c66ccb40e5.csv");
    stacks2.add("899cb9fa-c733-4879-b91d-91afd6fa0bb0.csv");
    stacks2.add("2a08d548-2895-42a0-937e-d88c41256996.csv");
    stacks2.add("bb5d43c3-23e2-453b-822f-80aea599be93.csv");

    CallStackDiff differ =
        new CallStackDiff(
            programName,
            5,
            option1,
            stacks1,
            option2,
            stacks2,
            "com.sleepycat.je.log.FileManager",
            "writeToFile",
            "(Ljava/io/RandomAccessFile;Ljava/nio/ByteBuffer;JJZ)I");
    differ.diff();
  }

  @Test
  public void force_TEMPORARY_FALSE_1() throws IOException, DiffException {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;

    String option1 = "TEMPORARY";
    Set<String> stacks1 = new HashSet<>();
    stacks1.add("dff8ff13-2c0f-4857-acad-68b39680f4b6.csv");
    stacks1.add("89448155-4dc2-40ee-8365-4ead4ba6f40b.csv");

    String option2 = "FALSE";
    Set<String> stacks2 = new HashSet<>();
    stacks2.add("f91dcff3-3572-410a-89bd-dc8995539160.csv");

    CallStackDiff differ =
        new CallStackDiff(
            programName,
            1,
            option1,
            stacks1,
            option2,
            stacks2,
            "com.sleepycat.je.log.FileManager$LogEndFileDescriptor",
            "force",
            "()V");
    differ.diff();
  }

  @Test
  public void force_TEMPORARY_FALSE_2() throws IOException, DiffException {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;

    String option1 = "TEMPORARY";
    Set<String> stacks1 = new HashSet<>();
    stacks1.add("dff8ff13-2c0f-4857-acad-68b39680f4b6.csv");
    stacks1.add("bb6719e9-4bea-4d43-8082-5d2ec4899b1c.csv");
    stacks1.add("89448155-4dc2-40ee-8365-4ead4ba6f40b.csv");

    String option2 = "FALSE";
    Set<String> stacks2 = new HashSet<>();
    stacks2.add("c5fa16a1-0258-4109-bc2a-9580c5457ff4.csv");

    CallStackDiff differ =
        new CallStackDiff(
            programName,
            2,
            option1,
            stacks1,
            option2,
            stacks2,
            "com.sleepycat.je.log.FileManager$LogEndFileDescriptor",
            "force",
            "()V");
    differ.diff();
  }

  @Test
  public void force_TEMPORARY_FALSE_3() throws IOException, DiffException {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;

    String option1 = "TEMPORARY";
    Set<String> stacks1 = new HashSet<>();
    stacks1.add("c25058e2-397a-4e78-82ee-04abf392c5b3.csv");
    stacks1.add("fd04f74b-4cf8-4507-b430-147774de9d79.csv");
    stacks1.add("e0e99123-de4d-4f35-84e3-183bf6e2a80e.csv");
    stacks1.add("bf06ab39-fddb-4acb-a361-dcef91bfc75d.csv");

    String option2 = "FALSE";
    Set<String> stacks2 = new HashSet<>();
    stacks2.add("0d2c03ec-ed6f-406f-ba58-6b757d93982d.csv");

    CallStackDiff differ =
        new CallStackDiff(
            programName,
            3,
            option1,
            stacks1,
            option2,
            stacks2,
            "com.sleepycat.je.log.FileManager$LogEndFileDescriptor",
            "force",
            "()V");
    differ.diff();
  }

  @Test
  public void force_TEMPORARY_FALSE_4() throws IOException, DiffException {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;

    String option1 = "TEMPORARY";
    Set<String> stacks1 = new HashSet<>();
    stacks1.add("fe9b0785-ed2e-4d51-bc72-1c283f3a733c.csv");

    String option2 = "FALSE";
    Set<String> stacks2 = new HashSet<>();
    stacks2.add("c96134c3-6cd1-4e87-842a-b585695a3d45.csv");

    CallStackDiff differ =
        new CallStackDiff(
            programName,
            4,
            option1,
            stacks1,
            option2,
            stacks2,
            "com.sleepycat.je.log.FileManager$LogEndFileDescriptor",
            "force",
            "()V");
    differ.diff();
  }

  @Test
  public void findEntry_TEMPORARY_FALSE_1() throws IOException, DiffException {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;

    String option1 = "TEMPORARY";
    Set<String> stacks1 = new HashSet<>();
    stacks1.add("459c8d3b-558f-4358-930e-50f128296e51.csv");

    String option2 = "FALSE";
    Set<String> stacks2 = new HashSet<>();
    stacks2.add("9297e0c6-c80a-4cc2-904f-65b71c4e990b.csv");

    CallStackDiff differ =
        new CallStackDiff(
            programName,
            1,
            option1,
            stacks1,
            option2,
            stacks2,
            "com.sleepycat.je.tree.IN",
            "findEntry",
            "([BZZLjava/util/Comparator;)I");
    differ.diff();
  }

  @Test
  public void findEntry_TEMPORARY_FALSE_2() throws IOException, DiffException {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;

    String option1 = "TEMPORARY";
    Set<String> stacks1 = new HashSet<>();
    stacks1.add("e1a372b6-bbde-4298-b78e-a6ef3e247288.csv");

    String option2 = "FALSE";
    Set<String> stacks2 = new HashSet<>();
    stacks2.add("fa7c54d7-d8e5-426c-acf5-f5708e55be8e.csv");

    CallStackDiff differ =
        new CallStackDiff(
            programName,
            2,
            option1,
            stacks1,
            option2,
            stacks2,
            "com.sleepycat.je.tree.IN",
            "findEntry",
            "([BZZLjava/util/Comparator;)I");
    differ.diff();
  }

  @Test
  public void findEntry_TEMPORARY_FALSE_3() throws IOException, DiffException {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;

    String option1 = "TEMPORARY";
    Set<String> stacks1 = new HashSet<>();
    stacks1.add("909355be-ddec-4744-b3bd-dd5cd98a314e.csv");

    String option2 = "FALSE";
    Set<String> stacks2 = new HashSet<>();
    stacks2.add("8b53f191-822c-4d48-ac6d-e78275cdc6f3.csv");
    stacks2.add("c0f60e43-267e-4a0c-beba-1a3894fdbb41.csv");
    stacks2.add("3bd0c42d-da6e-4e25-a1cc-bb13c8a90064.csv");
    stacks2.add("30793c24-2493-45ef-9f46-f53a8a6a0fdd.csv");
    stacks2.add("9ad7728d-6720-445d-8ece-94f2dc875609.csv");
    stacks2.add("bfa947d7-04c9-4774-a35f-7181368efc29.csv");
    stacks2.add("d87a96c5-d884-4a95-bf60-ec4c4e125164.csv");
    stacks2.add("ee826eb2-27f6-4896-a4d5-8468ad39fb73.csv");

    CallStackDiff differ =
        new CallStackDiff(
            programName,
            3,
            option1,
            stacks1,
            option2,
            stacks2,
            "com.sleepycat.je.tree.IN",
            "findEntry",
            "([BZZLjava/util/Comparator;)I");
    differ.diff();
  }

  @Test
  public void serialize_TEMPORARY_FALSE_1() throws IOException, DiffException {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;

    String option1 = "TEMPORARY";
    Set<String> stacks1 = new HashSet<>();
    stacks1.add("a1d82913-ff54-416c-ba90-e65efa4b588b.csv");

    String option2 = "FALSE";
    Set<String> stacks2 = new HashSet<>();
    stacks2.add("6ccd61c4-13f9-41f3-bf9c-9bec9f68930a.csv");
    stacks2.add("4a873061-7703-4bb2-9e77-709ff1e498e8.csv");
    stacks2.add("37d1c928-43a1-4a98-aa6e-e73015bbe023.csv");
    stacks2.add("31dad1ea-a0b6-40a8-b40f-1125ab9250b2.csv");
    stacks2.add("4a1c005b-4ec8-48e7-8f01-d40d8606d746.csv");
    stacks2.add("7e0c9195-ddb9-403a-b9fe-e3759c303845.csv");

    CallStackDiff differ =
        new CallStackDiff(
            programName,
            1,
            option1,
            stacks1,
            option2,
            stacks2,
            "com.sleepycat.je.tree.IN",
            "serialize",
            "(Ljava/nio/ByteBuffer;ZZ)V");
    differ.diff();
  }

  @Test
  public void serialize_TEMPORARY_FALSE_2() throws IOException, DiffException {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;

    String option1 = "TEMPORARY";
    Set<String> stacks1 = new HashSet<>();
    stacks1.add("9a10692c-eb30-420f-bb15-79fc92c4df82.csv");

    String option2 = "FALSE";
    Set<String> stacks2 = new HashSet<>();
    stacks2.add("37d1c928-43a1-4a98-aa6e-e73015bbe023.csv");
    stacks2.add("31dad1ea-a0b6-40a8-b40f-1125ab9250b2.csv");
    stacks2.add("7e0c9195-ddb9-403a-b9fe-e3759c303845.csv");
    stacks2.add("4a873061-7703-4bb2-9e77-709ff1e498e8.csv");

    stacks2.add("6ccd61c4-13f9-41f3-bf9c-9bec9f68930a.csv");
    stacks2.add("4a1c005b-4ec8-48e7-8f01-d40d8606d746.csv");

    CallStackDiff differ =
        new CallStackDiff(
            programName,
            2,
            option1,
            stacks1,
            option2,
            stacks2,
            "com.sleepycat.je.tree.IN",
            "serialize",
            "(Ljava/nio/ByteBuffer;ZZ)V");
    differ.diff();
  }

  @Test
  public void serialize_TEMPORARY_FALSE_3() throws IOException, DiffException {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;

    String option1 = "TEMPORARY";
    Set<String> stacks1 = new HashSet<>();
    stacks1.add("243bb709-8d79-4a47-a7f9-2ea6298cffa5.csv");

    String option2 = "FALSE";
    Set<String> stacks2 = new HashSet<>();
    stacks2.add("37d1c928-43a1-4a98-aa6e-e73015bbe023.csv");
    stacks2.add("31dad1ea-a0b6-40a8-b40f-1125ab9250b2.csv");
    stacks2.add("7e0c9195-ddb9-403a-b9fe-e3759c303845.csv");
    stacks2.add("4a873061-7703-4bb2-9e77-709ff1e498e8.csv");

    stacks2.add("6ccd61c4-13f9-41f3-bf9c-9bec9f68930a.csv");
    stacks2.add("4a1c005b-4ec8-48e7-8f01-d40d8606d746.csv");

    CallStackDiff differ =
        new CallStackDiff(
            programName,
            3,
            option1,
            stacks1,
            option2,
            stacks2,
            "com.sleepycat.je.tree.IN",
            "serialize",
            "(Ljava/nio/ByteBuffer;ZZ)V");
    differ.diff();
  }

  @Test
  public void serialize_TEMPORARY_FALSE_4() throws IOException, DiffException {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;

    String option1 = "TEMPORARY";
    Set<String> stacks1 = new HashSet<>();
    stacks1.add("a5f72690-cd6b-4091-a74b-7d473671eb23.csv");

    String option2 = "FALSE";
    Set<String> stacks2 = new HashSet<>();
    stacks2.add("37d1c928-43a1-4a98-aa6e-e73015bbe023.csv");
    stacks2.add("31dad1ea-a0b6-40a8-b40f-1125ab9250b2.csv");
    stacks2.add("7e0c9195-ddb9-403a-b9fe-e3759c303845.csv");
    stacks2.add("4a873061-7703-4bb2-9e77-709ff1e498e8.csv");

    stacks2.add("6ccd61c4-13f9-41f3-bf9c-9bec9f68930a.csv");
    stacks2.add("4a1c005b-4ec8-48e7-8f01-d40d8606d746.csv");

    CallStackDiff differ =
        new CallStackDiff(
            programName,
            4,
            option1,
            stacks1,
            option2,
            stacks2,
            "com.sleepycat.je.tree.IN",
            "serialize",
            "(Ljava/nio/ByteBuffer;ZZ)V");
    differ.diff();
  }

  @Test
  public void log_SEQUENTIAL_FALSE() throws IOException, DiffException {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;

    String option1 = "SEQUENTIAL";
    Set<String> stacks1 = new HashSet<>();
    //    stacks1.add("7d858428-991b-4f4d-a3dd-73be93f62771.csv");
    //    stacks1.add("59af756b-ca6b-4902-8838-ce20693c1775.csv");
    //    stacks1.add("dda4f9bb-7a39-41eb-939d-9425905246b2.csv");
    //    stacks1.add("cce6fa84-6287-4731-8039-585fa021ffbd.csv");
    //    stacks1.add("717b817b-e06d-4004-a2b4-749390e25790.csv");
    //    stacks1.add("50831c83-c855-4261-8583-e32ae580c98c.csv");
    //    stacks1.add("63db20f7-3c88-4b1a-930a-8631c8878790.csv");
    //    stacks1.add("654edfe2-a3c7-467f-8557-06ed403f40c8.csv");
    //    stacks1.add("468df092-db53-403b-a471-66c8a1782f5c.csv");
    //    stacks1.add("39f7047a-8a9a-48a9-abaf-ecf5cb5fefe1.csv");
    //    stacks1.add("600f4924-63de-4a37-972e-a3546ad07bd0.csv");
    //    stacks1.add("e4963505-8bbc-43cb-9be2-f5299fd7e5d1.csv");
    //    stacks1.add("a3994b52-dba9-40fe-86e2-f61f84360fda.csv");
    //    stacks1.add("95248a4c-1e91-40f1-8bb1-a8599c0a53cd.csv");
    //    stacks1.add("78955483-ea05-4940-bc31-852eded05673.csv");
    //    stacks1.add("e99ca1d8-b8c7-4fcd-8ec7-a69b8b194c77.csv");
    //    stacks1.add("0ff5b41b-9706-4e82-9003-ecaa19898f10.csv");
    //    stacks1.add("50dc8253-e507-4bb1-8150-0f522353c3a9.csv");
    //    stacks1.add("e1a6088a-eff9-4b18-8c38-0ac473623e66.csv");
    //    stacks1.add("eca1415f-00bf-4159-aa3f-2d44ede878f4.csv");
    //    stacks1.add("c4568abc-c73c-4536-9fb9-b773bb8af557.csv");
    //    stacks1.add("8335f4f8-1428-4508-837b-8c0b128e05ad.csv");
    //    stacks1.add("8e684181-2325-4345-bf3c-7fc106063a25.csv");
    //    stacks1.add("1d26be6d-e7a6-43ae-a373-9accfec1eae2.csv");
    //    stacks1.add("3007efff-d857-404b-9b33-50f76af50aa5.csv");
    //    stacks1.add("62be896f-1d59-418c-8d2e-62f5d143f1f4.csv");
    //    stacks1.add("90dbab77-77e9-421c-9129-d6ab3e3631ef.csv");
    //    stacks1.add("5bf72097-de42-435f-b6ed-a4e9a1b5d79d.csv");
    //    stacks1.add("a8db8cf9-9881-4975-8fe6-954ad6cfed37.csv");

    String option2 = "FALSE";
    Set<String> stacks2 = new HashSet<>();
    //    stacks2.add("9a9491f4-c3f3-41a5-b49a-55f357ffa633.csv");
    //    stacks2.add("a0a3ff89-92c3-42ef-8715-e276a4d18ef0.csv");
    //    stacks2.add("464e5bd8-b5c8-444c-a880-902b48809133.csv");
    //    stacks2.add("438d863e-ff2d-42e6-ba1c-d423c6e7892b.csv");
    //    stacks2.add("414aa048-36d2-4658-8555-c984d6dcbf40.csv");
    //    stacks2.add("bc52f70a-2c04-4276-b357-1f06ce600f3f.csv");
    //    stacks2.add("744da746-2f9d-4978-9c24-620afc8a1129.csv");
    //    stacks2.add("c19e2b6d-8ddc-4c8a-9bfb-fc7a3ac2bb95.csv");
    //    stacks2.add("ac3eca6e-469d-420b-a45f-2f4224142267.csv");
    //    stacks2.add("f81357a5-e11d-4860-a501-0bb259beb2fc.csv");
    //    stacks2.add("238334f5-948c-46c0-a9a5-4dc6f3c021f5.csv");
    //    stacks2.add("fb599616-cac5-4275-804b-ff926a32084c.csv");
    //    stacks2.add("4f068e65-9f35-453a-b763-b53c600e1c26.csv");
    //    stacks2.add("24ff5d60-5d8e-4526-baa8-fa07aefc0e7a.csv");
    //    stacks2.add("6a79b60d-532f-4238-8344-b38075f368ae.csv");
    //    stacks2.add("8502cd1e-ca10-4ef4-8fed-7dd1143fe863.csv");
    //    stacks2.add("3cf06d12-4a85-4723-8375-7c37d1b60168.csv");
    //    stacks2.add("f1608c5d-1acf-4715-bece-c5a3d85175c8.csv");
    //    stacks2.add("3a6a33f6-0c0d-429c-9853-e2b206066ff6.csv");
    //    stacks2.add("58c85d01-2ca6-45ca-80c4-49fa0672b2e0.csv");
    //    stacks2.add("2751c083-44af-4638-9067-978efe8de7de.csv");
    //    stacks2.add("719448d0-ae8f-4827-8964-02f2acba9bac.csv");
    //    stacks2.add("4b84b775-593a-4261-b008-ea90f0140b01.csv");
    //    stacks2.add("e13d1ed2-cbda-4b60-a4c7-e3f0472ab71f.csv");
    //    stacks2.add("3ef22ab5-ac5c-4d7d-8d9f-641e62a576da.csv");
    //    stacks2.add("c411d5f7-3234-4bfd-964a-1f819665cbca.csv");
    //    stacks2.add("3971a596-931a-4102-896b-18a3b207b4e8.csv");
    //    stacks2.add("6258d70d-e37a-455c-b7be-b58edad2b495.csv");
    //    stacks2.add("d32f0293-ab8d-4c56-ba1d-abf698d8cce9.csv");
    //    stacks2.add("23102ac1-c9d9-4f5f-a64d-46df0d809771.csv");
    //    stacks2.add("0cc5cde3-caa5-4103-a415-07a8416f70ca.csv");
    //    stacks2.add("2f32a9b0-0cbd-4416-aef0-7e19ba6e039c.csv");
    //    stacks2.add("8e5b222a-2b81-49b2-8266-36884cd029d9.csv");
    //    stacks2.add("eb8651ad-bb09-4d5e-9d55-185ee0717c7d.csv");
    //    stacks2.add("ab29c314-63c6-4d30-a374-15b1f6e51fec.csv");
    //    stacks2.add("f887fff8-ea2e-4fa4-b72d-0190bc009ff3.csv");
    //    stacks2.add("b84a0481-33bb-4c54-9f72-dae06c430561.csv");
    //    stacks2.add("e0b2323c-44b4-44b7-aa1f-cdca822dc6dc.csv");
    //    stacks2.add("3c7f2253-d677-42a2-a929-e2d708bbc492.csv");
    //    stacks2.add("40f677a0-4f40-4c0c-ac2f-9d5b0775d5b9.csv");
    //    stacks2.add("9560022c-e71f-4c3f-8f26-335718b4605c.csv");
    //    stacks2.add("e114a041-b6fe-4db5-98a0-02be1027339b.csv");
    //    stacks2.add("822d7ad3-b3a8-4072-85d8-6606dbdf3f79.csv");
    //    stacks2.add("0b67a732-dfb8-433d-a046-d8a4b145a6d0.csv");
    //    stacks2.add("29a84058-4a17-4843-92ec-750b5ea25559.csv");
    //    stacks2.add("0b3dddbd-8869-4698-a88c-05b647bb937d.csv");
    //    stacks2.add("7a2051e5-4b33-40c8-9443-451ff54f8e51.csv");
    //    stacks2.add("fc2d078d-6df0-4823-9b60-e17b19a36c50.csv");
    //    stacks2.add("2cd72f2c-947d-4856-8d7c-6be722db1578.csv");

    CallStackDiff differ =
        new CallStackDiff(
            programName,
            1,
            option1,
            stacks1,
            option2,
            stacks2,
            "com.sleepycat.je.log.LogManager",
            "log",
            "(Lcom/sleepycat/je/log/LogParams;)Lcom/sleepycat/je/log/LogItem;");
    differ.diff();
  }

  @Test
  public void log_SEQUENTIAL_FALSE_2() throws IOException, DiffException {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;

    String option1 = "SEQUENTIAL";
    Set<String> stacks1 = new HashSet<>();
    stacks1.add("c8677b65-8859-4da7-9f73-7dda1af4b545.csv");

    String option2 = "FALSE";
    Set<String> stacks2 = new HashSet<>();
    stacks2.add("00c098ad-7c05-42ad-9a56-1c59a429a931.csv");

    CallStackDiff differ =
        new CallStackDiff(
            programName,
            2,
            option1,
            stacks1,
            option2,
            stacks2,
            "com.sleepycat.je.log.LogManager",
            "log",
            "(Lcom/sleepycat/je/log/LogParams;)Lcom/sleepycat/je/log/LogItem;");
    differ.diff();
  }

  @Test
  public void log_SEQUENTIAL_FALSE_3() throws IOException, DiffException {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;

    String option1 = "SEQUENTIAL";
    Set<String> stacks1 = new HashSet<>();
    stacks1.add("3c4ea72e-7357-4af6-8aee-fd3ec22eec5f.csv");

    String option2 = "FALSE";
    Set<String> stacks2 = new HashSet<>();
    stacks2.add("0323f017-c3ef-4684-81f2-6f2a2fd96b97.csv");

    CallStackDiff differ =
        new CallStackDiff(
            programName,
            3,
            option1,
            stacks1,
            option2,
            stacks2,
            "com.sleepycat.je.log.LogManager",
            "log",
            "(Lcom/sleepycat/je/log/LogParams;)Lcom/sleepycat/je/log/LogItem;");
    differ.diff();
  }

  @Test
  public void addPostMarshallingInfo_SEQUENTIAL_FALSE_1() throws IOException, DiffException {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;

    String option1 = "SEQUENTIAL";
    Set<String> stacks1 = new HashSet<>();
    stacks1.add("1331bbb5-8df3-4f76-b506-95d280b8434a.csv");

    String option2 = "FALSE";
    Set<String> stacks2 = new HashSet<>();
    stacks2.add("dacc7903-29d2-49ed-bb29-7b1a3e4717e3.csv");

    CallStackDiff differ =
        new CallStackDiff(
            programName,
            1,
            option1,
            stacks1,
            option2,
            stacks2,
            "com.sleepycat.je.log.LogEntryHeader",
            "addPostMarshallingInfo",
            "(Ljava/nio/ByteBuffer;JLcom/sleepycat/je/utilint/VLSN;)Ljava/nio/ByteBuffer;");
    differ.diff();
  }

  @Test
  public void addPostMarshallingInfo_SEQUENTIAL_FALSE_2() throws IOException, DiffException {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;

    String option1 = "SEQUENTIAL";
    Set<String> stacks1 = new HashSet<>();
    stacks1.add("8d1d1a38-7437-4576-b19a-55e1b086eb8d.csv");

    String option2 = "FALSE";
    Set<String> stacks2 = new HashSet<>();
    stacks2.add("ae1d17de-5e17-4172-b69a-62b37a4f0065.csv");

    CallStackDiff differ =
        new CallStackDiff(
            programName,
            2,
            option1,
            stacks1,
            option2,
            stacks2,
            "com.sleepycat.je.log.LogEntryHeader",
            "addPostMarshallingInfo",
            "(Ljava/nio/ByteBuffer;JLcom/sleepycat/je/utilint/VLSN;)Ljava/nio/ByteBuffer;");
    differ.diff();
  }

  @Test
  public void addPostMarshallingInfo_SEQUENTIAL_FALSE_3() throws IOException, DiffException {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;

    String option1 = "SEQUENTIAL";
    Set<String> stacks1 = new HashSet<>();
    stacks1.add("898501cd-a8e1-4468-9868-e9378a4c791c.csv");

    String option2 = "FALSE";
    Set<String> stacks2 = new HashSet<>();
    stacks2.add("9f069bdd-1022-4fef-9733-7a9ddc54b608.csv");
    stacks2.add("2e4ae861-b290-4548-8a4c-b5ec2ea6a364.csv");

    CallStackDiff differ =
        new CallStackDiff(
            programName,
            3,
            option1,
            stacks1,
            option2,
            stacks2,
            "com.sleepycat.je.log.LogEntryHeader",
            "addPostMarshallingInfo",
            "(Ljava/nio/ByteBuffer;JLcom/sleepycat/je/utilint/VLSN;)Ljava/nio/ByteBuffer;");
    differ.diff();
  }

  @Test
  public void addPostMarshallingInfo_SEQUENTIAL_FALSE_4() throws IOException, DiffException {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;

    String option1 = "SEQUENTIAL";
    Set<String> stacks1 = new HashSet<>();
    stacks1.add("08c13200-f2b9-4274-95cb-67ab71f4147b.csv");

    String option2 = "FALSE";
    Set<String> stacks2 = new HashSet<>();
    stacks2.add("d6429d1c-ebd8-40f4-88ac-db1bf349284a.csv");
    stacks2.add("99ea8f65-b9e1-40c0-81b1-dde4ec3e9685.csv");

    CallStackDiff differ =
        new CallStackDiff(
            programName,
            4,
            option1,
            stacks1,
            option2,
            stacks2,
            "com.sleepycat.je.log.LogEntryHeader",
            "addPostMarshallingInfo",
            "(Ljava/nio/ByteBuffer;JLcom/sleepycat/je/utilint/VLSN;)Ljava/nio/ByteBuffer;");
    differ.diff();
  }

  @Test
  public void addPostMarshallingInfo_SEQUENTIAL_FALSE_5() throws IOException, DiffException {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;

    String option1 = "SEQUENTIAL";
    Set<String> stacks1 = new HashSet<>();
    stacks1.add("81c30a84-2b44-4664-a037-71489777a733.csv");

    String option2 = "FALSE";
    Set<String> stacks2 = new HashSet<>();
    stacks2.add("d6429d1c-ebd8-40f4-88ac-db1bf349284a.csv");
    stacks2.add("99ea8f65-b9e1-40c0-81b1-dde4ec3e9685.csv");

    CallStackDiff differ =
        new CallStackDiff(
            programName,
            5,
            option1,
            stacks1,
            option2,
            stacks2,
            "com.sleepycat.je.log.LogEntryHeader",
            "addPostMarshallingInfo",
            "(Ljava/nio/ByteBuffer;JLcom/sleepycat/je/utilint/VLSN;)Ljava/nio/ByteBuffer;");
    differ.diff();
  }

  @Test
  public void addPostMarshallingInfo_SEQUENTIAL_FALSE_6() throws IOException, DiffException {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;

    String option1 = "SEQUENTIAL";
    Set<String> stacks1 = new HashSet<>();
    stacks1.add("401f83bc-62c0-4b24-9b38-317eea5f91e2.csv");

    String option2 = "FALSE";
    Set<String> stacks2 = new HashSet<>();
    stacks2.add("ca3e4963-2a92-4ff8-be27-379594698720.csv");

    CallStackDiff differ =
        new CallStackDiff(
            programName,
            6,
            option1,
            stacks1,
            option2,
            stacks2,
            "com.sleepycat.je.log.LogEntryHeader",
            "addPostMarshallingInfo",
            "(Ljava/nio/ByteBuffer;JLcom/sleepycat/je/utilint/VLSN;)Ljava/nio/ByteBuffer;");
    differ.diff();
  }

  @Test
  public void readFromFileInternal_SEQUENTIAL_FALSE() throws IOException, DiffException {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;

    String option1 = "SEQUENTIAL";
    Set<String> stacks1 = new HashSet<>();

    String option2 = "FALSE";
    Set<String> stacks2 = new HashSet<>();

    CallStackDiff differ =
        new CallStackDiff(
            programName,
            1,
            option1,
            stacks1,
            option2,
            stacks2,
            "com.sleepycat.je.log.FileManager",
            "readFromFileInternal",
            "(Ljava/io/RandomAccessFile;Ljava/nio/ByteBuffer;JJ)V");
    differ.diff();
  }

  @Test
  public void writeToFile_SEQUENTIAL_FALSE_1() throws IOException, DiffException {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;

    String option1 = "SEQUENTIAL";
    Set<String> stacks1 = new HashSet<>();
    stacks1.add("66edcde3-b171-4ece-8afb-0ce14a5ed1e2.csv");

    String option2 = "FALSE";
    Set<String> stacks2 = new HashSet<>();
    stacks2.add("e2e98772-ed95-4ba8-b9f2-f9a2b7a2faf7.csv");

    CallStackDiff differ =
        new CallStackDiff(
            programName,
            1,
            option1,
            stacks1,
            option2,
            stacks2,
            "com.sleepycat.je.log.FileManager",
            "writeToFile",
            "(Ljava/io/RandomAccessFile;Ljava/nio/ByteBuffer;JJZ)I");
    differ.diff();
  }

  @Test
  public void writeToFile_SEQUENTIAL_FALSE_2() throws IOException, DiffException {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;

    String option1 = "SEQUENTIAL";
    Set<String> stacks1 = new HashSet<>();
    stacks1.add("7bb9f51b-f966-48c0-9440-68084a98e027.csv");

    String option2 = "FALSE";
    Set<String> stacks2 = new HashSet<>();
    stacks2.add("2a08d548-2895-42a0-937e-d88c41256996.csv");

    CallStackDiff differ =
        new CallStackDiff(
            programName,
            2,
            option1,
            stacks1,
            option2,
            stacks2,
            "com.sleepycat.je.log.FileManager",
            "writeToFile",
            "(Ljava/io/RandomAccessFile;Ljava/nio/ByteBuffer;JJZ)I");
    differ.diff();
  }

  @Test
  public void writeToFile_SEQUENTIAL_FALSE_3() throws IOException, DiffException {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;

    String option1 = "SEQUENTIAL";
    Set<String> stacks1 = new HashSet<>();
    stacks1.add("77c762c9-0710-4e1b-b2b1-0b4f0ab39cee.csv");

    String option2 = "FALSE";
    Set<String> stacks2 = new HashSet<>();
    stacks2.add("b36be923-4c00-49e4-8f19-899a1fbd17cb.csv");
    stacks2.add("baf4e43d-5024-456c-b87f-8415604bde0f.csv");
    stacks2.add("43cf293a-46bf-434a-9d30-69c66ccb40e5.csv");
    stacks2.add("899cb9fa-c733-4879-b91d-91afd6fa0bb0.csv");
    stacks2.add("bb5d43c3-23e2-453b-822f-80aea599be93.csv");
    stacks2.add("0b3e9166-c6ab-40a2-8a1f-3673bddfc536.csv");

    CallStackDiff differ =
        new CallStackDiff(
            programName,
            3,
            option1,
            stacks1,
            option2,
            stacks2,
            "com.sleepycat.je.log.FileManager",
            "writeToFile",
            "(Ljava/io/RandomAccessFile;Ljava/nio/ByteBuffer;JJZ)I");
    differ.diff();
  }

  @Test
  public void force_SEQUENTIAL_FALSE_1() throws IOException, DiffException {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;

    String option1 = "SEQUENTIAL";
    Set<String> stacks1 = new HashSet<>();
    stacks1.add("0d0f66d1-a826-41e6-8ed4-d0550c88d1db.csv");

    String option2 = "FALSE";
    Set<String> stacks2 = new HashSet<>();
    stacks2.add("f91dcff3-3572-410a-89bd-dc8995539160.csv");

    CallStackDiff differ =
        new CallStackDiff(
            programName,
            1,
            option1,
            stacks1,
            option2,
            stacks2,
            "com.sleepycat.je.log.FileManager$LogEndFileDescriptor",
            "force",
            "()V");
    differ.diff();
  }

  @Test
  public void force_SEQUENTIAL_FALSE_2() throws IOException, DiffException {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;

    String option1 = "SEQUENTIAL";
    Set<String> stacks1 = new HashSet<>();
    stacks1.add("13ab9168-6da0-412a-84db-6f9045a7d30b.csv");

    String option2 = "FALSE";
    Set<String> stacks2 = new HashSet<>();
    stacks2.add("c5fa16a1-0258-4109-bc2a-9580c5457ff4.csv");

    CallStackDiff differ =
        new CallStackDiff(
            programName,
            2,
            option1,
            stacks1,
            option2,
            stacks2,
            "com.sleepycat.je.log.FileManager$LogEndFileDescriptor",
            "force",
            "()V");
    differ.diff();
  }

  @Test
  public void force_SEQUENTIAL_FALSE_3() throws IOException, DiffException {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;

    String option1 = "SEQUENTIAL";
    Set<String> stacks1 = new HashSet<>();
    stacks1.add("dc7d37b2-03f9-4e7f-b2c1-9aa132023eb7.csv");
    stacks1.add("69c4d958-c0b2-467e-a935-3e46a50e6f31.csv");
    stacks1.add("1d6a1588-5125-4216-a158-d31a8d737642.csv");

    String option2 = "FALSE";
    Set<String> stacks2 = new HashSet<>();
    stacks2.add("0d2c03ec-ed6f-406f-ba58-6b757d93982d.csv");

    CallStackDiff differ =
        new CallStackDiff(
            programName,
            3,
            option1,
            stacks1,
            option2,
            stacks2,
            "com.sleepycat.je.log.FileManager$LogEndFileDescriptor",
            "force",
            "()V");
    differ.diff();
  }

  @Test
  public void force_SEQUENTIAL_FALSE_4() throws IOException, DiffException {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;

    String option1 = "SEQUENTIAL";
    Set<String> stacks1 = new HashSet<>();
    stacks1.add("0b43f36f-7fdc-4667-bcd8-d60278b98514.csv");

    String option2 = "FALSE";
    Set<String> stacks2 = new HashSet<>();
    stacks2.add("c96134c3-6cd1-4e87-842a-b585695a3d45.csv");

    CallStackDiff differ =
        new CallStackDiff(
            programName,
            4,
            option1,
            stacks1,
            option2,
            stacks2,
            "com.sleepycat.je.log.FileManager$LogEndFileDescriptor",
            "force",
            "()V");
    differ.diff();
  }

  @Test
  public void findEntry_SEQUENTIAL_FALSE_1() throws IOException, DiffException {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;

    String option1 = "SEQUENTIAL";
    Set<String> stacks1 = new HashSet<>();
    stacks1.add("4ec425c9-39f6-43e2-8282-d3b4961d39ad.csv");

    String option2 = "FALSE";
    Set<String> stacks2 = new HashSet<>();
    stacks2.add("8b53f191-822c-4d48-ac6d-e78275cdc6f3.csv");
    stacks2.add("c0f60e43-267e-4a0c-beba-1a3894fdbb41.csv");
    stacks2.add("9297e0c6-c80a-4cc2-904f-65b71c4e990b.csv");
    stacks2.add("3bd0c42d-da6e-4e25-a1cc-bb13c8a90064.csv");
    stacks2.add("30793c24-2493-45ef-9f46-f53a8a6a0fdd.csv");
    stacks2.add("9ad7728d-6720-445d-8ece-94f2dc875609.csv");
    stacks2.add("bfa947d7-04c9-4774-a35f-7181368efc29.csv");
    stacks2.add("fa7c54d7-d8e5-426c-acf5-f5708e55be8e.csv");
    stacks2.add("d87a96c5-d884-4a95-bf60-ec4c4e125164.csv");
    stacks2.add("ee826eb2-27f6-4896-a4d5-8468ad39fb73.csv");

    CallStackDiff differ =
        new CallStackDiff(
            programName,
            1,
            option1,
            stacks1,
            option2,
            stacks2,
            "com.sleepycat.je.tree.IN",
            "findEntry",
            "([BZZLjava/util/Comparator;)I");
    differ.diff();
  }

  @Test
  public void serialize_SEQUENTIAL_FALSE_1() throws IOException, DiffException {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;

    String option1 = "SEQUENTIAL";
    Set<String> stacks1 = new HashSet<>();
    stacks1.add("a0a02281-46c7-4052-b5cc-9f80a89e78f5.csv");

    String option2 = "FALSE";
    Set<String> stacks2 = new HashSet<>();
    stacks2.add("4a873061-7703-4bb2-9e77-709ff1e498e8.csv");
    stacks2.add("7e0c9195-ddb9-403a-b9fe-e3759c303845.csv");
    stacks2.add("37d1c928-43a1-4a98-aa6e-e73015bbe023.csv");
    stacks2.add("31dad1ea-a0b6-40a8-b40f-1125ab9250b2.csv");
    stacks2.add("6ccd61c4-13f9-41f3-bf9c-9bec9f68930a.csv");
    stacks2.add("4a1c005b-4ec8-48e7-8f01-d40d8606d746.csv");

    CallStackDiff differ =
        new CallStackDiff(
            programName,
            1,
            option1,
            stacks1,
            option2,
            stacks2,
            "com.sleepycat.je.tree.IN",
            "serialize",
            "(Ljava/nio/ByteBuffer;ZZ)V");
    differ.diff();
  }

  @Test
  public void serialize_SEQUENTIAL_FALSE_2() throws IOException, DiffException {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;

    String option1 = "SEQUENTIAL";
    Set<String> stacks1 = new HashSet<>();
    stacks1.add("e4c73395-03da-4cd6-b498-6f84a0e792e5.csv");

    String option2 = "FALSE";
    Set<String> stacks2 = new HashSet<>();
    stacks2.add("4a873061-7703-4bb2-9e77-709ff1e498e8.csv");
    stacks2.add("7e0c9195-ddb9-403a-b9fe-e3759c303845.csv");
    stacks2.add("37d1c928-43a1-4a98-aa6e-e73015bbe023.csv");
    stacks2.add("31dad1ea-a0b6-40a8-b40f-1125ab9250b2.csv");
    stacks2.add("6ccd61c4-13f9-41f3-bf9c-9bec9f68930a.csv");
    stacks2.add("4a1c005b-4ec8-48e7-8f01-d40d8606d746.csv");

    CallStackDiff differ =
        new CallStackDiff(
            programName,
            2,
            option1,
            stacks1,
            option2,
            stacks2,
            "com.sleepycat.je.tree.IN",
            "serialize",
            "(Ljava/nio/ByteBuffer;ZZ)V");
    differ.diff();
  }
}
